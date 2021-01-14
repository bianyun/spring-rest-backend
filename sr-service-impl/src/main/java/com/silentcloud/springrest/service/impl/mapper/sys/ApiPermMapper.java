package com.silentcloud.springrest.service.impl.mapper.sys;

import com.silentcloud.springrest.model.entity.sys.ApiPerm;
import com.silentcloud.springrest.repository.sys.ApiPermRepository;
import com.silentcloud.springrest.service.api.dto.sys.ApiPermDto;
import com.silentcloud.springrest.service.impl.mapper.AbstractCycleAvoidingMappingContext;
import org.mapstruct.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.Nullable;

import java.util.List;
import java.util.Set;

@Mapper
public abstract class ApiPermMapper {
    @Autowired
    private ApiPermRepository apiPermRepository;

    @Named("entityToDto")
    public ApiPermDto entityToDto(ApiPerm entity) {
        return entityToDto(entity, new ApiPermCycleAvoidingMappingContext());
    }

    @Named("dtoToEntity")
    public ApiPerm dtoToEntity(ApiPermDto dto) {
        return dtoToEntity(dto, new ApiPermCycleAvoidingMappingContext());
    }

    @IterableMapping(qualifiedByName = "entityToDto")
    public abstract List<ApiPermDto> entityListToDtoList(List<ApiPerm> entityList);

    @IterableMapping(qualifiedByName = "entityToDto")
    public abstract Set<ApiPermDto> entitySetToDtoSet(Set<ApiPerm> entityList);

    @Mapping(target = "permLevel", ignore = true)
    @Nullable
    @Mapping(target = "parent", ignore = true)
    abstract ApiPermDto entityToDto(ApiPerm entity, @Context ApiPermCycleAvoidingMappingContext context);

    @Mapping(target = "menus", ignore = true)
    @Mapping(target = "buttons", ignore = true)
    protected abstract ApiPerm dtoToEntity(ApiPermDto dto, @Context ApiPermCycleAvoidingMappingContext context);

    @AfterMapping
    public void establishRelations(ApiPermDto dto, @MappingTarget ApiPerm entity) {
        ApiPermDto parentApiPermDto = dto.getParent();
        if (parentApiPermDto != null && parentApiPermDto.getId() != null) {
            entity.setParent(apiPermRepository.getOne(parentApiPermDto.getId()));
        }
    }

    static class ApiPermCycleAvoidingMappingContext extends AbstractCycleAvoidingMappingContext<Long, ApiPerm, ApiPermDto> {
    }
}
