package com.silentcloud.springrest.model.enums.base.converter;

import com.silentcloud.springrest.model.enums.base.EnumConst;
import com.silentcloud.springrest.model.enums.base.helper.EnumConstHelper;

import javax.persistence.AttributeConverter;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

/**
 * A base {@link AttributeConverter} to be implemented by enum converters.
 *
 * @author bianyun
 */
public class BaseEnumConstAttributeConverter<E extends Enum<E> & EnumConst<E, ID>, ID>
        implements AttributeConverter<E, ID> {

    @Override
    public final ID convertToDatabaseColumn(E attribute) {
        return attribute == null ? null : attribute.getId();
    }

    @Override
    public final E convertToEntityAttribute(ID dbData) {
        // if the value in the DB is null we will return null as well
        if (dbData == null) {
            return null;
        }
        // Otherwise, a helper is used to find a matching enum constant,
        // and if one cannot be found an exception is thrown.
        return helper().byIdOrThrow(
                dbData,
                () -> new IllegalStateException(
                        "There is no corresponding enum for the given value from the database")
        );
    }

    /**
     * @return a {@link EnumConstHelper} for an enum converted by this converter
     */
    @SuppressWarnings("unchecked")
    public final EnumConstHelper<E, ID> helper() {
        Type[] types = ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments();
        Class<E> actualEnumClass = (Class<E>) types[0];
        return EnumConstHelper.of(actualEnumClass);
    }
}
