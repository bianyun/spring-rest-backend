package com.silentcloud.springrest.service.api.module.sys;

import com.silentcloud.springrest.model.entity.sys.User;
import com.silentcloud.springrest.service.api.dto.sys.UserDto;
import com.silentcloud.springrest.service.api.module.BaseService;

public interface UserService extends BaseService<Long, User, UserDto> {

    UserDto findByUsername(String username);

    UserDto findByEmail(String email);

    UserDto findByMobile(String mobile);

    void setPasswordById(Long id, String plainPassword);

    void resetPasswordById(Long id);

}
