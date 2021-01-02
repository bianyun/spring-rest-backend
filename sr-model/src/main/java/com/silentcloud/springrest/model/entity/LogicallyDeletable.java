package com.silentcloud.springrest.model.entity;

import cn.hutool.core.util.StrUtil;
import org.springframework.data.domain.Persistable;

public interface LogicallyDeletable extends Persistable<Long> {
    String NEW_DELETED_VALUE_TEMPLATE = "[deleted]_{}_[id={}]";
    String DELETED_PROPERTY_NAME = "deleted";

    boolean isDeleted();

    void setDeleted(boolean deleted);

    void logicallyDelete();

    default String generateDeletedValue(String oldValue) {
        return StrUtil.format(NEW_DELETED_VALUE_TEMPLATE, oldValue, getId());
    }
}
