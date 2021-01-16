package com.silentcloud.springrest.service.impl.mapper.sys;

import com.silentcloud.springrest.model.entity.sys.Role;
import com.silentcloud.springrest.service.api.dto.sys.RoleDto;
import com.silentcloud.springrest.service.impl.mapper.BaseMapper;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@SuppressWarnings({"SpringJavaAutowiredMembersInspection", "RedundantSuppression"})
@Mapper
public abstract class RoleMapper implements BaseMapper<Long, Role, RoleDto> {

    public abstract RoleDto entityToDto(Role entity);

    @Mapping(target = "lastModifiedTime", ignore = true)
    @Mapping(target = "lastModifiedBy", ignore = true)
    @Mapping(target = "createdTime", ignore = true)
    @Mapping(target = "createdBy", ignore = true)
    @Mapping(target = "users", ignore = true)
    @Mapping(target = "menus", ignore = true)
    @Mapping(target = "buttons", ignore = true)
    @Override
    public abstract Role dtoToEntity(RoleDto dto);

    @Mapping(target = "lastModifiedTime", ignore = true)
    @Mapping(target = "lastModifiedBy", ignore = true)
    @Mapping(target = "createdTime", ignore = true)
    @Mapping(target = "createdBy", ignore = true)
    @Mapping(target = "users", ignore = true)
    @Mapping(target = "menus", ignore = true)
    @Mapping(target = "buttons", ignore = true)
    @Override
    public abstract void updateEntityFromDto(RoleDto dto, @MappingTarget Role entity);

}
