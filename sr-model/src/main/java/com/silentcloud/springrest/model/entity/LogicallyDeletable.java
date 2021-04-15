package com.silentcloud.springrest.model.entity;

import cn.hutool.core.util.StrUtil;
import org.springframework.data.domain.Persistable;

/**
 * 可逻辑删除接口
 *
 * @author bianyun
 */
public interface LogicallyDeletable extends Persistable<Long> {
    String NEW_DELETED_VALUE_TEMPLATE = "[deleted]_{}_[id={}]";
    String DELETED_PROPERTY_NAME = "deleted";

    /**
     * 检查是否已删除
     *
     * @return 是否已删除
     */
    boolean isDeleted();

    /**
     * 设置是否已删除状态
     *
     * @param deleted 是否已删除
     */
    void setDeleted(boolean deleted);

    /**
     * 逻辑删除操作
     */
    void logicallyDelete();

    /**
     * 根据 旧值 生成唯一的逻辑删除后的新值（保证字段唯一性）
     *
     * @param oldValue 旧值
     * @return 逻辑删除后的新值
     */
    default String generateDeletedValue(String oldValue) {
        return StrUtil.format(NEW_DELETED_VALUE_TEMPLATE, oldValue, getId());
    }
}
