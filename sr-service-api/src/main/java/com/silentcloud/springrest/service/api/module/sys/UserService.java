package com.silentcloud.springrest.service.api.module.sys;

import com.silentcloud.springrest.model.entity.sys.User;
import com.silentcloud.springrest.service.api.dto.sys.*;
import com.silentcloud.springrest.service.api.module.BaseService;

import java.util.Set;

public interface UserService extends BaseService<Long, User, UserDto> {

    UserDto findByUsername(String username);

    UserDto findByEmail(String email);

    UserDto findByMobile(String mobile);

    boolean isPasswordValid(Long id, String plainPassword);

    void setPasswordById(Long id, String plainPassword);

    void resetPasswordById(Long id);

    Set<RoleDto> getRolesByUserId(Long userId);

    Set<ApiPermDto> getApiPermsByUserId(Long userId);

    Set<MenuDto> getMenuPermsByUserId(Long userId);

    Set<ButtonDto> getButtonPermsByUserId(Long userId);
}
