package com.silentcloud.springrest.service.impl.meta;

import com.silentcloud.springrest.service.api.dto.BaseDto;
import org.springframework.data.domain.Persistable;

import java.io.Serializable;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 实体元数据Map
 *
 * @author bianyun
 */
public final class EntityMetaDataMap {

    private static final
    Map<Class<?>, EntityMetaData<?, ? extends BaseDto<?, ?>, ? extends Persistable<?>>> MAP = new ConcurrentHashMap<>();

    private EntityMetaDataMap() {
        throw new AssertionError("deliberately prohibit instantiation");
    }

    public static <ID extends Serializable, DTO extends BaseDto<ID, Entity>, Entity extends Persistable<ID>>
    void put(Class<?> dtoOrEntityClass, EntityMetaData<ID, DTO, Entity> entityMetaData) {
        MAP.put(dtoOrEntityClass, entityMetaData);
    }

    @SuppressWarnings("unchecked")
    public static <ID extends Serializable, DTO extends BaseDto<ID, Entity>, Entity extends Persistable<ID>>
    EntityMetaData<ID, DTO, Entity>
    get(Class<?> dtoOrEntityClass) {
        return (EntityMetaData<ID, DTO, Entity>) MAP.get(dtoOrEntityClass);
    }

}
