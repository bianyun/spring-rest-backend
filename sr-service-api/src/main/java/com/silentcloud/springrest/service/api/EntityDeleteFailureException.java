package com.silentcloud.springrest.service.api;

import cn.hutool.core.util.StrUtil;
import cn.hutool.http.HttpStatus;

public class EntityDeleteFailureException extends CustomServiceLayerException {
    private static final long serialVersionUID = 3459297462471041509L;
    private static final int httpStatusCode = HttpStatus.HTTP_CONFLICT;
    private static final String ERROR = "资源删除异常";
    private static final String MSG_TEMPLATE = "此{}还存在 {} 条关联的{}记录，无法删除";

    @SuppressWarnings("unused")
    public EntityDeleteFailureException(String label, Object id, long relatedObjectCount, String relatedObjectLabel) {
        super(httpStatusCode, ERROR, StrUtil.format(MSG_TEMPLATE, label, relatedObjectCount, relatedObjectLabel));
    }

}
