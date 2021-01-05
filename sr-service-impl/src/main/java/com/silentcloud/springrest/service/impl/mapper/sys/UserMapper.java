package com.silentcloud.springrest.service.impl.mapper.sys;

import com.silentcloud.springrest.model.entity.sys.User;
import com.silentcloud.springrest.service.api.dto.sys.UserDto;
import com.silentcloud.springrest.service.impl.mapper.BaseMapper;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper
public interface UserMapper extends BaseMapper<Long, User, UserDto> {

    @Mapping(target = "roles", ignore = true)
    @Mapping(target = "lastModifiedTime", ignore = true)
    @Mapping(target = "lastModifiedBy", ignore = true)
    @Mapping(target = "deleted", ignore = true)
    @Mapping(target = "createdTime", ignore = true)
    @Mapping(target = "createdBy", ignore = true)
    @Mapping(target = "password", ignore = true)
    @Override
    void updateEntityFromDto(UserDto userDto, @MappingTarget User user);
}

