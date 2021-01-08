package com.silentcloud.springrest.web.controller.sys;

import cn.hutool.core.util.StrUtil;
import com.github.xiaoymin.knife4j.annotations.ApiSupport;
import com.silentcloud.springrest.common.Success;
import com.silentcloud.springrest.service.api.dto.sys.UserDto;
import com.silentcloud.springrest.web.vo.LoginFormData;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.UnauthenticatedException;
import org.apache.shiro.authz.UnauthorizedException;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.apache.shiro.subject.Subject;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Api(tags = "登录管理")
@ApiSupport(order = 0)
@RestController
@RequestMapping("/sys/auth")
public class AuthController {

    @GetMapping("/page/401")
    public void unauthenticated() {
        throw new UnauthenticatedException("会话超时，请重新登录");
    }

    @GetMapping("/page/403")
    public void unauthorized() {
        throw new UnauthorizedException("用户没有权限访问");
    }

    @ApiOperation("登录")
    @PostMapping("/login")
    public Success login(@Valid @RequestBody LoginFormData loginFormData) {
        String username = loginFormData.getUsername();
        String password = loginFormData.getPassword();

        AuthenticationToken authenticationToken = new UsernamePasswordToken(username, password);

        Subject subject = SecurityUtils.getSubject();
        String successMsg = StrUtil.format("用户 {} 登录成功", username);
        if (subject.isAuthenticated()) {
            UserDto currentUser = (UserDto) subject.getPrincipal();
            if (currentUser.getUsername().equals(username)) {
                successMsg = StrUtil.format("用户 {} 已经是登录状态", username);
            } else {
                subject.logout();
                subject.login(authenticationToken);
            }
        } else {
            subject.login(authenticationToken);
        }
        return Success.of(successMsg, username);
    }

    @ApiOperation("退出")
    @PostMapping("/logout")
    public Success logout() {
        Subject subject = SecurityUtils.getSubject();
        UserDto currentUser = (UserDto) subject.getPrincipal();
        subject.logout();
        String successMsg = StrUtil.format("用户 {} 已退出", currentUser.getUsername());
        return Success.of(successMsg);
    }
}
