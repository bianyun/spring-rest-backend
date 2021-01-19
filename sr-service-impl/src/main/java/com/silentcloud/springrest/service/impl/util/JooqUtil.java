package com.silentcloud.springrest.service.impl.util;

import com.silentcloud.springrest.model.enums.Country;
import com.silentcloud.springrest.model.enums.base.EnumConst;
import com.silentcloud.springrest.service.api.query.response.FlatQueryRecord;
import lombok.experimental.UtilityClass;
import org.jooq.*;

import java.util.*;
import java.util.stream.Collectors;

@UtilityClass
public class JooqUtil {
    public static final String DELIMETER_BETWEEN_TABLE_AND_COLUMN = "__";
    public static final String DELIMETER_BETWEEN_ENUMCLASS_AND_FIELD = "_#_";
    public static final String GROUP_FIELDS_SEPARATOR = ", ";

    public static final List<Class<? extends EnumConst<?, ?>>> ENUM_DICT_MAP_BLACK_LIST;
    static {
        ENUM_DICT_MAP_BLACK_LIST = new ArrayList<>();
        ENUM_DICT_MAP_BLACK_LIST.add(Country.class);
    }

    public String buildFieldAlias(Field<?> field) {
        if (field instanceof TableField) {
            TableField<?, ?> tableField = (TableField<?, ?>) field;
            Table<?> table = Objects.requireNonNull(tableField.getTable());
            return (table.getName() + DELIMETER_BETWEEN_TABLE_AND_COLUMN + tableField.getName()).toLowerCase();
        } else {
            return field.getName();
        }
    }

    public Field<?> attachAlias(Field<?> field) {
        return field.as(buildFieldAlias(field));
    }

    public List<Field<?>> addAliasForTableFields(Field<?>[] fields) {
        return addAliasForTableFields(Arrays.asList(fields));
    }

    public List<Field<?>> addAliasForTableFields(Collection<? extends Field<?>> fields) {
        List<Field<?>> result = new ArrayList<>();
        for (Field<?> field : fields) {
            result.add(attachAlias(field));
        }
        return result;
    }

    public <T> T getFieldValue(FlatQueryRecord flatQueryRecord, Field<T> field) {
        String recordKey = buildFieldAlias(field);
        Object value = flatQueryRecord.get(recordKey);
        return field.getType().cast(value);
    }

    public Set<String> getLegalOuterFieldNames(TableLike<? extends Record> nestedTable) {
        return Arrays.stream(nestedTable.fields()).map(Field::getName).collect(Collectors.toSet());
    }
}
