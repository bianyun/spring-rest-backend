package com.silentcloud.springrest.service.impl.util;

import cn.hutool.core.annotation.AnnotationUtil;
import cn.hutool.core.util.ClassUtil;
import cn.hutool.core.util.ReflectUtil;
import com.github.wenhao.jpa.Specifications;
import com.silentcloud.springrest.model.entity.LogicallyDeletable;
import com.silentcloud.springrest.service.api.dto.BaseDto;
import com.silentcloud.springrest.service.api.query.parser.QueryConditionExprParser;
import com.silentcloud.springrest.service.impl.mapper.BaseMapper;
import com.silentcloud.springrest.service.impl.meta.EntityMetaData;
import com.silentcloud.springrest.service.impl.meta.EntityMetaDataMap;
import org.jooq.Condition;
import org.jooq.Record;
import org.jooq.TableLike;
import org.springframework.data.domain.Persistable;
import org.springframework.data.jpa.domain.Specification;

import java.io.Serializable;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.math.BigInteger;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.*;


public final class JpaUtil {
    public static final String ENTITY_ID_PROPERTY_NAME = "id";
    private static final List<Class<?>> ELEMENTARY_CLASSES = Arrays.asList(String.class, Byte.class, Character.class,
            Short.class, Integer.class, Long.class, Float.class, Double.class, BigInteger.class,
            LocalDate.class, LocalDateTime.class, LocalTime.class, Serializable.class);

    private JpaUtil() {
        throw new AssertionError("deliberately prohibit instantiation");
    }

    public static <ID extends Serializable, Entity extends Persistable<ID>>
    boolean isEntityLogicallyDeletable(Class<Entity> entityClass) {
        return EntityMetaDataMap.get(entityClass).isEntityLogicallyDeletable();
    }

    public static <ID extends Serializable, Entity extends Persistable<ID>>
    boolean isEntityActivatable(Class<Entity> entityClass) {
        return EntityMetaDataMap.get(entityClass).isEntityActivatable();
    }

    public static <ID extends Serializable, Entity extends Persistable<ID>>
    Specification<Entity> buildFindByIdSpec(Class<Entity> entityClass, ID id) {
        boolean isEntityLogicallyDeletable = isEntityLogicallyDeletable(entityClass);
        return Specifications.<Entity>and()
                .eq(ENTITY_ID_PROPERTY_NAME, id)
                .eq(isEntityLogicallyDeletable, LogicallyDeletable.DELETED_PROPERTY_NAME, false)
                .build();
    }

    public static List<Field> getAnnotatedFieldsByAnnotationType(Class<?> clazz, Class<? extends Annotation> annotationType) {
        List<Field> resultList = new ArrayList<>();
        Field[] fields = ClassUtil.getDeclaredFields(clazz);
        if (fields == null) {
            return resultList;
        }

        for (Field field : fields) {
            if (AnnotationUtil.getAnnotation(field, annotationType) != null) {
                resultList.add(field);
            }
        }

        return resultList;
    }

    public static <ID extends Serializable, Entity extends Persistable<ID>>
    Set<String> getLegalPropNamesCanBeUsedInJpaQueryExpr(Class<Entity> entityClass) {
        Set<String> resultSet = new HashSet<>();
        Field[] allFields = ReflectUtil.getFields(entityClass);

        for (Field field : allFields) {
            Class<?> fieldType = field.getType();
            if (Modifier.isStatic(field.getModifiers())) {
                continue;
            }

            if (fieldType.isPrimitive() || fieldType.isEnum() || ELEMENTARY_CLASSES.contains(fieldType)) {
                resultSet.add(field.getName());
            }
        }

        return resultSet;
    }

    public static <ID extends Serializable, DTO extends BaseDto<ID, Entity>, Entity extends Persistable<ID>>
    QueryConditionExprParser<Specification<Entity>> getJpaQueryConditionExprParser(Class<Entity> entityClass) {
        EntityMetaData<ID, DTO, Entity> entityMetaData = EntityMetaDataMap.get(entityClass);
        return entityMetaData.getJpaQueryConditionExprParser();
    }

    public static QueryConditionExprParser<Condition> getFlatQueryConditionExprParser(Class<?> entityClass) {
        return EntityMetaDataMap.get(entityClass).getFlatQueryConditionExprParser();
    }

    public static TableLike<? extends Record> getJooqNestedTable(Class<?> entityClass) {
        return EntityMetaDataMap.get(entityClass).getJooqNestedTable();
    }


    public static <ID extends Serializable, DTO extends BaseDto<ID, Entity>, Entity extends Persistable<ID>>
    BaseMapper<ID, Entity, DTO> getMapper(Class<Entity> entityClass) {
        EntityMetaData<ID, DTO, Entity> entityMetaData = EntityMetaDataMap.get(entityClass);
        return entityMetaData.getMapper();
    }

}
