package com.silentcloud.springrest.service.impl.mapper;

import com.silentcloud.springrest.service.api.dto.BaseDto;
import org.mapstruct.BeforeMapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.TargetType;
import org.springframework.data.domain.Persistable;

import java.io.Serializable;
import java.util.IdentityHashMap;
import java.util.Map;

/**
 * 避免循环映射上下文
 *
 * @author bianyun
 */
public abstract class AbstractCycleAvoidingMappingContext<ID extends Serializable,
        Entity extends Persistable<ID>, DTO extends BaseDto<ID, Entity>> {

    private final Map<Entity, DTO> knownInstances = new IdentityHashMap<>();
    private final Map<DTO, Entity> inverseKnownInstances = new IdentityHashMap<>();

    @SuppressWarnings("unused")
    @BeforeMapping
    public DTO getMappedInstance(Entity source, @TargetType Class<DTO> targetType) {
        return knownInstances.get(source);
    }

    @BeforeMapping
    public void storeMappedInstance(Entity source, @MappingTarget DTO target) {
        knownInstances.put(source, target);
    }

    @SuppressWarnings("unused")
    @BeforeMapping
    public Entity getInverseMappedInstance(DTO source, @TargetType Class<Entity> targetType) {
        return inverseKnownInstances.get(source);
    }

    @BeforeMapping
    public void storeInverseMappedInstance(DTO source, @MappingTarget Entity target) {
        inverseKnownInstances.put(source, target);
    }
}
