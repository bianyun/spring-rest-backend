package com.silentcloud.springrest.service.impl.meta;

import com.silentcloud.springrest.service.api.dto.BaseDto;
import org.springframework.data.domain.Persistable;

import java.io.Serializable;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public final class EntityMetaDataMap {

    private static final Map<Class<?>, EntityMetaData<?, ? extends BaseDto<?, ?>, ? extends Persistable<?>>> map = new ConcurrentHashMap<>();

    private EntityMetaDataMap() {
        throw new AssertionError("deliberately prohibit instantiation");
    }

    public static <ID extends Serializable, DTO extends BaseDto<ID, Entity>, Entity extends Persistable<ID>>
    void put(Class<?> dtoOrEntityClass, EntityMetaData<ID, DTO, Entity> entityMetaData) {
        map.put(dtoOrEntityClass, entityMetaData);
    }

    @SuppressWarnings("unchecked")
    public static <ID extends Serializable, DTO extends BaseDto<ID, Entity>, Entity extends Persistable<ID>>
    EntityMetaData<ID, DTO, Entity>
    get(Class<?> dtoOrEntityClass) {
        return (EntityMetaData<ID, DTO, Entity>) map.get(dtoOrEntityClass);
    }

}
