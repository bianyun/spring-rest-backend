package com.silentcloud.springrest.web.controller.sys;

import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import com.github.xiaoymin.knife4j.annotations.ApiSupport;
import com.silentcloud.springrest.model.entity.sys.User;
import com.silentcloud.springrest.service.api.dto.sys.RoleDto;
import com.silentcloud.springrest.service.api.dto.sys.UserDto;
import com.silentcloud.springrest.service.api.module.sys.UserService;
import com.silentcloud.springrest.service.api.query.FlatQueryService;
import com.silentcloud.springrest.service.api.query.JpaQueryService;
import com.silentcloud.springrest.web.IllegalWebOperationException;
import com.silentcloud.springrest.web.controller.AbstractActivatableController;
import com.silentcloud.springrest.web.shiro.authz.annotation.RequiresPerm;
import com.silentcloud.springrest.web.shiro.authz.annotation.RequiresPermViewDetail;
import com.silentcloud.springrest.web.vo.UpdatePasswordFormData;
import com.silentcloud.springrest.web.vo.UserProfile;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;
import java.util.Set;

import static com.silentcloud.springrest.web.util.Consts.API_PERM_PREFIX;
import static com.silentcloud.springrest.web.util.Consts.SUBCLASS_API_OPERATION_ORDER_OFFSET;

/**
 * 用户管理
 *
 * @author bianyun
 */
@Api(tags = "用户管理")
@ApiSupport(order = 1)
@RequestMapping("/sys/users")
@RestController
public class UserController extends AbstractActivatableController<Long, User, UserDto> {
    private final UserService userService;

    @Value("${demo-mode.enabled:false}")
    private boolean demoModeEnabled;

    @Value("#{'${demo-mode.preserved-users:}'.split('\\s*,\\s*')}")
    private List<String> demoPreservedUsers;

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
    public UserProfile getCurrentUserProfile() {
        UserProfile profileVo = new UserProfile();

        Long currentUserId = getCurrentUserId();
        UserDto currentUser = userService.findById(currentUserId);
        profileVo.setUser(currentUser);

        assert currentUser != null;
        if (currentUser.getUsername().equals(UserDto.PREDEFINED_USER_SUPERADMIN)) {
            profileVo.setRoles(Collections.singletonList(RoleDto.ROLE_SUPERADMIN));
        } else {
            profileVo.setRoles(userService.getRolesByUserId(currentUserId));
            profileVo.setMenuPerms(userService.getMenuPermsByUserId(currentUserId));
            profileVo.setBtnPerms(userService.getButtonPermsByUserId(currentUserId));
            profileVo.setApiPerms(userService.getApiPermsByUserId(currentUserId));
        }
        profileVo.setDemoModeEnabled(demoModeEnabled);
        profileVo.setDemoPreservedUsers(demoPreservedUsers);

        return profileVo;
    }

    @ApiOperationSupport(order = SUBCLASS_API_OPERATION_ORDER_OFFSET + 2)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @ApiOperation("更新当前登录用户的密码")
    @PostMapping("/update-password-for-current-user")
    public void updatePasswordForCurrentUser(@RequestBody UpdatePasswordFormData formData) {
        String oldPassword = formData.getOldPassword();
        String newPassword = formData.getNewPassword();

        checkDemoModeOperation(getCurrentUsername(), "修改内置演示用户的密码");

        Long currentUserId = getCurrentUserId();
        if (!userService.isPasswordValid(currentUserId, oldPassword)) {
            throw new IllegalArgumentException("用户的原密码不正确");
        }

        userService.setPasswordById(currentUserId, newPassword);
    }

    @ApiOperationSupport(order = SUBCLASS_API_OPERATION_ORDER_OFFSET + 3)
    @RequiresPermViewDetail
    @ApiOperation("获取用户的角色列表")
    @GetMapping("/{id}/roles")
    public List<RoleDto> getRolesByUserId(@PathVariable Long id) {
        return userService.getRolesByUserId(id);
    }

    @ApiOperationSupport(order = SUBCLASS_API_OPERATION_ORDER_OFFSET + 4)
    @RequiresPerm(name = "保存用户角色", value = API_PERM_PREFIX + "save-assigned-roles")
    @ApiOperation("保存用户分配的角色")
    @PutMapping("/{id}/roles")
    public void saveAssignedRolesByUserId(@PathVariable Long id, @RequestBody Set<Long> roleIds) {
        checkDemoModeOperation(id, "修改内置演示用户分配的角色");

        userService.saveRoleIdsByUserId(id, roleIds);
    }

    private void checkDemoModeOperation(String targetUsername, String operationDesc) {
        if (demoModeEnabled && demoPreservedUsers.contains(targetUsername)) {
            throw new IllegalWebOperationException("当前处于演示模式，不能" + operationDesc);
        }
    }

    @SuppressWarnings("SameParameterValue")
    private void checkDemoModeOperation(Long targetUserId, String operationDesc) {
        if (demoModeEnabled) {
            UserDto user = userService.findById(targetUserId);
            if (user != null && demoPreservedUsers.contains(user.getUsername())) {
                checkDemoModeOperation(user.getUsername(), operationDesc);
            }
        }
    }
}
