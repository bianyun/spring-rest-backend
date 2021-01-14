package com.silentcloud.springrest.service.api.module.sys;

import com.silentcloud.springrest.model.entity.sys.User;
import com.silentcloud.springrest.service.api.dto.sys.*;
import com.silentcloud.springrest.service.api.module.BaseService;
import org.springframework.lang.Nullable;

import java.util.List;
import java.util.Set;

public interface UserService extends BaseService<Long, User, UserDto> {

    @Nullable
    UserDto findByUsername(String username);

    @Nullable
    UserDto findByEmail(String email);

    @Nullable
    UserDto findByMobile(String mobile);

    boolean isPasswordValid(Long id, String plainPassword);

    void setPasswordById(Long id, String plainPassword);

    void resetPasswordById(Long id);

    List<RoleDto> getRolesByUserId(Long userId);

    Set<ApiPermDto> getApiPermsByUserId(Long userId);

    Set<MenuDto> getMenuPermsByUserId(Long userId);

    Set<ButtonDto> getButtonPermsByUserId(Long userId);

    void saveRoleIdsByUserId(Long id, Set<Long> roleIds);
}
