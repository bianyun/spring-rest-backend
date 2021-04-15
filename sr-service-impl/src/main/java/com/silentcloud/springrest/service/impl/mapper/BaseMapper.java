package com.silentcloud.springrest.service.impl.mapper;

import com.silentcloud.springrest.service.api.dto.BaseDto;
import org.mapstruct.MappingTarget;
import org.springframework.data.domain.Persistable;

import java.io.Serializable;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * 公共 Mapper接口
 *
 * @author bianyun
 */
@SuppressWarnings({
        "EmptyMethod",
        "unused",
        "AlibabaAbstractMethodOrInterfaceMethodMustUseJavadoc"
})
public interface BaseMapper<ID extends Serializable, Entity extends Persistable<ID>, DTO extends BaseDto<ID, Entity>> {

    DTO entityToDto(Entity entity);

    Entity dtoToEntity(DTO dto);

    List<DTO> entityListToDtoList(List<Entity> entityList);

    Set<DTO> entitySetToDtoSet(Set<Entity> entitySet);

    List<Entity> dtoListToEntityList(List<DTO> dtoList);

    Set<Entity> dtoSetToEntitySet(Set<DTO> dtoSet);

    void updateEntityFromDto(DTO dto, @MappingTarget Entity entity);

    default <K extends Serializable, E extends Persistable<K>>
    Set<K> getDtoIds(List<? extends BaseDto<K, E>> dtos) {
        return dtos.stream().map(BaseDto::getId).collect(Collectors.toSet());
    }

    default <K extends Serializable, E extends Persistable<K>>
    Set<K> getEntityIds(List<E> entities) {
        return entities.stream().map(Persistable::getId).collect(Collectors.toSet());
    }
}
