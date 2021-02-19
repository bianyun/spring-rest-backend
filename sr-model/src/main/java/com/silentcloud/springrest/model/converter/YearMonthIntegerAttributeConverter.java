package com.silentcloud.springrest.model.converter;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.time.YearMonth;

@Converter(autoApply = true)
public class YearMonthIntegerAttributeConverter implements AttributeConverter<YearMonth, Integer> {

    @Override
    public Integer convertToDatabaseColumn(YearMonth attribute) {
        if (attribute != null) {
            return (attribute.getYear() * 100) +
                    attribute.getMonth().getValue();
        }
        return null;
    }

    @Override
    public YearMonth convertToEntityAttribute(Integer dbData) {
        if (dbData != null) {
            int year = dbData / 100;
            int month = dbData % 100;
            return YearMonth.of(year, month);
        }
        return null;
    }
}
