package com.silentcloud.springrest.model.enums.base;

/**
 * 枚举接口
 *
 * @author bianyun
 */
@SuppressWarnings("unused")
public interface EnumConst<E extends Enum<E> & EnumConst<E, ID>, ID> {

    /**
     * 获取枚举对象ID
     *
     * @return 枚举对象ID
     */
    ID getId();

    /**
     * 获取枚举对象中文名称
     *
     * @return 枚举对象中文名称
     */
    String getLabel();

}
