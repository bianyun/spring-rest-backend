package com.silentcloud.springrest.model.enums.base;

@SuppressWarnings("unused")
public interface EnumConst<E extends Enum<E> & EnumConst<E, ID>, ID> {

    ID getId();

    String getLabel();

}
