package com.silentcloud.springrest.service.api.module.sys;

import com.silentcloud.springrest.model.entity.sys.Role;
import com.silentcloud.springrest.service.api.dto.sys.ButtonDto;
import com.silentcloud.springrest.service.api.dto.sys.MenuDto;
import com.silentcloud.springrest.service.api.dto.sys.RoleDto;
import com.silentcloud.springrest.service.api.module.BaseService;

import java.util.Set;

/**
 * 角色服务接口
 *
 * @author bianyun
 */
public interface RoleService extends BaseService<Long, Role, RoleDto> {

    /**
     * 根据 角色ID 获取相关的菜单集合
     *
     * @param roleId 角色ID
     * @return 相关的菜单集合
     */
    Set<MenuDto> getMenusByRoleId(Long roleId);

    /**
     * 根据 角色ID 获取相关的按钮集合
     *
     * @param roleId 角色ID
     * @return 相关的按钮集合
     */
    Set<ButtonDto> getButtonsByRoleId(Long roleId);

    /**
     * 为角色分配菜单权限和按钮权限
     *
     * @param roleId           角色ID
     * @param menuPermValueSet 菜单权限值集合
     * @param btnPermValueSet  按钮权限值集合
     */
    void assignPerms(Long roleId, Set<String> menuPermValueSet, Set<String> btnPermValueSet);
}
