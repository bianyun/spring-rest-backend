package com.silentcloud.springrest.service.api;

import cn.hutool.core.util.StrUtil;
import cn.hutool.http.HttpStatus;

public class LogicallyDeletedEntityActivateFailureException extends CustomServiceLayerException {
    private static final long serialVersionUID = 3217909287389913650L;
    private static final int httpStatusCode = HttpStatus.HTTP_FORBIDDEN;
    private static final String ERROR_TEMPLATE = "资源{}异常";
    private static final String MSG_TEMPLATE = "{}[id={}] 已被逻辑删除, 无法{}";

    public LogicallyDeletedEntityActivateFailureException(String label, Object id, String actionLabel) {
        super(httpStatusCode, StrUtil.format(ERROR_TEMPLATE, actionLabel),
                StrUtil.format(MSG_TEMPLATE, label, id, actionLabel));
    }

}
