package com.silentcloud.springrest.web.shiro.realm;

import cn.hutool.core.util.StrUtil;
import com.silentcloud.springrest.service.api.dto.sys.ApiPermDto;
import com.silentcloud.springrest.service.api.dto.sys.RoleDto;
import com.silentcloud.springrest.service.api.dto.sys.UserDto;
import com.silentcloud.springrest.service.api.module.sys.UserService;
import com.silentcloud.springrest.web.shiro.authc.PasswordMatcher;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.Set;
import java.util.stream.Collectors;

@Component("authorizer")
public class UserRealm extends AuthorizingRealm {
    private final UserService userService;

    public UserRealm(PasswordMatcher matcher, @Lazy UserService userService) {
        super(matcher);
        this.userService = userService;
    }

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        UserDto userDto = (UserDto) getAvailablePrincipal(principals);

        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        Set<String> roles;
        Set<String> stringPermissions;
        if (userDto.getUsername().equals(UserDto.PREDEFINED_USER_SUPERADMIN)) {
            roles = Collections.singleton(RoleDto.PREDEFINED_ROLE_SUPERADMIN);
            stringPermissions = Collections.singleton("*");
        } else {
            roles = userService.getRolesByUserId(userDto.getId()).stream()
                    .map(RoleDto::getValue).collect(Collectors.toSet());
            stringPermissions = userService.getApiPermsByUserId(userDto.getId()).stream()
                    .map(ApiPermDto::getValue).collect(Collectors.toSet());
        }
        info.setRoles(roles);
        info.setStringPermissions(stringPermissions);
        return info;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        String username = ((UsernamePasswordToken) token).getUsername();
        if (StrUtil.isBlank(username)) {
            throw new AuthenticationException("用户名不能为空");
        }

        UserDto userDto = userService.findByUsername(username);
        if (userDto == null) {
            throw new AuthenticationException("用户账号或密码错误");
        }

        if (!userDto.isActive()) {
            throw new AuthenticationException("用户账号已被停用");
        }

        return new SimpleAuthenticationInfo(userDto, userDto.getPassword(), getName());
    }

}
