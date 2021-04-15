package com.silentcloud.springrest.model.entity;

/**
 * 可启用停用接口
 *
 * @author bianyun
 */
public interface Activatable {

    /**
     * 检查是否已启用
     *
     * @return 是否已启用
     */
    boolean isActive();

    /**
     * 设置是否启用
     *
     * @param active 是否启用
     */
    void setActive(boolean active);

}
