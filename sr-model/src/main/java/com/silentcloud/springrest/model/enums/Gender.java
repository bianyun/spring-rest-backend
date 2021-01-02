package com.silentcloud.springrest.model.enums;

import com.silentcloud.springrest.model.enums.base.EnumConst;
import com.silentcloud.springrest.model.enums.base.converter.BaseEnumConstAttributeConverter;
import com.silentcloud.springrest.model.enums.base.helper.EnumConstHelper;

import javax.persistence.Converter;


public enum Gender implements EnumConst<Gender, Integer> {
    MALE(1, "男"),
    FEMALE(2, "女"),
    UNSPECIFIED(3, "未知");

    public static final EnumConstHelper<Gender, Integer> HELPER = EnumConstHelper.of(Gender.class);
    private final Integer id;
    private final String name;

    Gender(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    @Override
    public Integer getId() {
        return id;
    }

    @Override
    public String getName() {
        return name;
    }

    @SuppressWarnings("unused")
    @Converter(autoApply = true)
    public static class EnumAttributeConverter extends BaseEnumConstAttributeConverter<Gender, Integer> {
    }

}
