package com.silentcloud.springrest.web.controller.sys;

import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import com.github.xiaoymin.knife4j.annotations.ApiSupport;
import com.silentcloud.springrest.model.entity.sys.Role;
import com.silentcloud.springrest.service.api.dto.sys.ButtonDto;
import com.silentcloud.springrest.service.api.dto.sys.MenuDto;
import com.silentcloud.springrest.service.api.dto.sys.RoleDto;
import com.silentcloud.springrest.service.api.module.sys.RoleService;
import com.silentcloud.springrest.service.api.query.FlatQueryService;
import com.silentcloud.springrest.service.api.query.JpaQueryService;
import com.silentcloud.springrest.web.controller.AbstractBaseController;
import com.silentcloud.springrest.web.shiro.authz.annotation.RequiresPerm;
import com.silentcloud.springrest.web.vo.AssignedPermsForRole;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Set;
import java.util.stream.Collectors;

import static com.silentcloud.springrest.web.util.Consts.API_PERM_PREFIX;
import static com.silentcloud.springrest.web.util.Consts.SUBCLASS_API_OPERATION_ORDER_OFFSET;

@Api(tags = "角色管理")
@ApiSupport(order = 2)
@RequestMapping("/sys/roles")
@RestController
public class RoleController extends AbstractBaseController<Long, Role, RoleDto> {
    private final RoleService roleService;

    @Autowired
    public RoleController(JpaQueryService jpaQueryService,
                          FlatQueryService flatQueryService,
                          RoleService roleService) {
        super(jpaQueryService, flatQueryService, roleService);
        this.roleService = roleService;
    }

    @ApiOperationSupport(order = SUBCLASS_API_OPERATION_ORDER_OFFSET + 1)
    @RequiresPerm(name = "获取角色分配的权限", value = API_PERM_PREFIX + "fetch-perms")
    @ApiOperation("获取角色分配的权限")
    @GetMapping("/{id}/assigned-perms")
    public AssignedPermsForRole getAssignedPermsByRoleId(@PathVariable Long id) {

        Set<MenuDto> menus = roleService.getMenusByRoleId(id);
        Set<ButtonDto> buttons = roleService.getButtonsByRoleId(id);
        Set<String> menuPermValues = menus.stream().map(MenuDto::getValue).collect(Collectors.toSet());
        Set<String> btnPermValues = buttons.stream().map(ButtonDto::getValue).collect(Collectors.toSet());

        AssignedPermsForRole result = new AssignedPermsForRole();
        result.setMenuPermValues(menuPermValues);
        result.setBtnPermValues(btnPermValues);

        return result;
    }

    @ApiOperationSupport(order = SUBCLASS_API_OPERATION_ORDER_OFFSET + 2)
    @RequiresPerm(name = "保存角色分配的权限", value = API_PERM_PREFIX + "save-perms")
    @ApiOperation("保存角色分配的权限")
    @PutMapping("/{id}/assigned-perms")
    public void assignPermsByRoleId(@PathVariable Long id, @RequestBody AssignedPermsForRole assignedPerms) {
        roleService.assignPerms(id, assignedPerms.getMenuPermValues(), assignedPerms.getBtnPermValues());
    }
}
