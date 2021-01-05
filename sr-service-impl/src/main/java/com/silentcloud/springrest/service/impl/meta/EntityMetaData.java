package com.silentcloud.springrest.service.impl.meta;

import com.silentcloud.springrest.model.entity.Activatable;
import com.silentcloud.springrest.model.entity.LogicallyDeletable;
import com.silentcloud.springrest.service.api.dto.BaseDto;
import com.silentcloud.springrest.service.impl.mapper.BaseMapper;
import com.silentcloud.springrest.util.LabelUtil;
import lombok.Data;
import org.springframework.data.domain.Persistable;

import java.io.Serializable;

@Data
public class EntityMetaData<ID extends Serializable, DTO extends BaseDto<ID, Entity>,
        Entity extends Persistable<ID>> implements Serializable {
    private static final long serialVersionUID = -625844930859127153L;

    private final Class<DTO> dtoClass;
    private final Class<Entity> entityClass;
    private final boolean entityLogicallyDeletable;
    private final boolean entityActivatable;
    private final String label;
    private BaseMapper<ID, Entity, DTO> mapper;

    private EntityMetaData(Class<DTO> dtoClass, Class<Entity> entityClass) {
        this.dtoClass = dtoClass;
        this.entityClass = entityClass;
        entityLogicallyDeletable = LogicallyDeletable.class.isAssignableFrom(entityClass);
        entityActivatable = Activatable.class.isAssignableFrom(entityClass);
        label = LabelUtil.getClassLabel(dtoClass);
    }

    public static <ID extends Serializable, DTO extends BaseDto<ID, Entity>, Entity extends Persistable<ID>>
    EntityMetaData<ID, DTO, Entity> of(Class<DTO> dtoClass, Class<Entity> entityClass) {
        return new EntityMetaData<>(dtoClass, entityClass);
    }

}
