package com.silentcloud.springrest.web.controller.sys;

import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import com.github.xiaoymin.knife4j.annotations.ApiSupport;
import com.silentcloud.springrest.model.entity.sys.User;
import com.silentcloud.springrest.service.api.dto.sys.RoleDto;
import com.silentcloud.springrest.service.api.dto.sys.UserDto;
import com.silentcloud.springrest.service.api.module.sys.UserService;
import com.silentcloud.springrest.service.api.query.FlatQueryService;
import com.silentcloud.springrest.service.api.query.JpaQueryService;
import com.silentcloud.springrest.web.controller.AbstractActivatableController;
import com.silentcloud.springrest.web.vo.UpdatePasswordFormData;
import com.silentcloud.springrest.web.vo.UserProfileVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;

import static com.silentcloud.springrest.web.util.Consts.SUBCLASS_API_OPERATION_ORDER_OFFSET;

@Api(tags = "用户管理")
@ApiSupport(order = 1)
@RequestMapping("/sys/users")
@RestController
public class UserController extends AbstractActivatableController<Long, User, UserDto> {
    private final UserService userService;

    @Autowired
    public UserController(JpaQueryService jpaQueryService,
                          FlatQueryService flatQueryService,
                          UserService userService) {
        super(jpaQueryService, flatQueryService, userService);
        this.userService = userService;
    }

    @ApiOperationSupport(order = SUBCLASS_API_OPERATION_ORDER_OFFSET + 1)
    @ApiOperation("获取当前登录用户的资料")
    @GetMapping("/current-user-profile")
    public UserProfileVo getCurrentUserProfile() {
        UserDto currentUser = getCurrentUser();
        Long currentUserId = currentUser.getId();

        UserProfileVo profileVo = new UserProfileVo();
        profileVo.setUser(currentUser);
        profileVo.setApiPerms(userService.getApiPermsByUserId(currentUserId));
        profileVo.setMenuPerms(userService.getMenuPermsByUserId(currentUserId));
        profileVo.setButtonPerms(userService.getButtonPermsByUserId(currentUserId));

        if (currentUser.getUsername().equals(UserDto.PREDEFINED_USER_SUPERADMIN)) {
            profileVo.setRoles(Collections.singleton(RoleDto.ROLE_SUPERADMIN));
        } else {
            profileVo.setRoles(userService.getRolesByUserId(currentUserId));
        }

        return profileVo;
    }

    @ApiOperationSupport(order = SUBCLASS_API_OPERATION_ORDER_OFFSET + 2)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @ApiOperation("更新当前登录用户的密码")
    @PostMapping("/update-password-for-current-user")
    public void updatePasswordForCurrentUser(@RequestBody UpdatePasswordFormData formData) {
        String oldPassword = formData.getOldPassword();
        String newPassword = formData.getNewPassword();

        Long currentUserId = getCurrentUser().getId();
        if (!userService.isPasswordValid(currentUserId, oldPassword)) {
            throw new IllegalArgumentException("用户的原密码不正确");
        }

        userService.setPasswordById(currentUserId, newPassword);
    }

    private static UserDto getCurrentUser() {
        Subject subject = SecurityUtils.getSubject();
        return (UserDto) subject.getPrincipal();
    }
}
