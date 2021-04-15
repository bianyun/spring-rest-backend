package com.silentcloud.springrest.model.enums;

import com.fasterxml.jackson.annotation.JsonEnumDefaultValue;
import com.silentcloud.springrest.model.enums.base.EnumConst;
import com.silentcloud.springrest.model.enums.base.converter.BaseEnumConstAttributeConverter;
import com.silentcloud.springrest.model.enums.base.helper.EnumConstHelper;

import javax.persistence.Converter;

/**
 * 枚举类 - 性别
 *
 * @author bianyun
 */

@SuppressWarnings("AlibabaEnumConstantsMustHaveComment")
public enum Gender implements EnumConst<Gender, Integer> {

    MALE(1, "男"),

    FEMALE(2, "女"),

    @JsonEnumDefaultValue
    UNSPECIFIED(3, "未知");

    public static final EnumConstHelper<Gender, Integer> HELPER = EnumConstHelper.of(Gender.class);
    private final Integer id;
    private final String label;

    Gender(Integer id, String label) {
        this.id = id;
        this.label = label;
    }

    @Override
    public Integer getId() {
        return id;
    }

    @Override
    public String getLabel() {
        return label;
    }

    @SuppressWarnings("unused")
    @Converter(autoApply = true)
    public static class EnumAttributeConverter extends BaseEnumConstAttributeConverter<Gender, Integer> {
    }

}
