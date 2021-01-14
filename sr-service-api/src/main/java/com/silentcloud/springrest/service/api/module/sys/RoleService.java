package com.silentcloud.springrest.service.api.module.sys;

import com.silentcloud.springrest.model.entity.sys.Role;
import com.silentcloud.springrest.service.api.dto.sys.ButtonDto;
import com.silentcloud.springrest.service.api.dto.sys.MenuDto;
import com.silentcloud.springrest.service.api.dto.sys.RoleDto;
import com.silentcloud.springrest.service.api.module.BaseService;

import java.util.Set;

public interface RoleService extends BaseService<Long, Role, RoleDto> {

    Set<MenuDto> getMenusByRoleId(Long roleId);

    Set<ButtonDto> getButtonsByRoleId(Long roleId);

    void assignPerms(Long roleId, Set<String> menuPermValueSet, Set<String> btnPermValueSet);
}
