package com.silentcloud.springrest.service.impl.mapper.sys;

import com.silentcloud.springrest.model.entity.sys.User;
import com.silentcloud.springrest.service.api.common.PasswordEncoder;
import com.silentcloud.springrest.service.api.dto.sys.UserDto;
import com.silentcloud.springrest.service.impl.mapper.BaseMapper;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.springframework.beans.factory.annotation.Autowired;

@Mapper
public abstract class UserMapper implements BaseMapper<Long, User, UserDto> {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Mapping(target = "roles", ignore = true)
    @Mapping(target = "lastModifiedTime", ignore = true)
    @Mapping(target = "lastModifiedBy", ignore = true)
    @Mapping(target = "deleted", ignore = true)
    @Mapping(target = "createdTime", ignore = true)
    @Mapping(target = "createdBy", ignore = true)
    @Mapping(target = "password", ignore = true)
    @Override
    public abstract void updateEntityFromDto(UserDto dto, @MappingTarget User entity);

    @AfterMapping
    public void encodePasswordWhenCreateUser(UserDto dto, @MappingTarget User entity) {
        if (dto.getId() == null) {
            entity.setPassword(passwordEncoder.encode(dto.getPassword()));
        }
    }
}

