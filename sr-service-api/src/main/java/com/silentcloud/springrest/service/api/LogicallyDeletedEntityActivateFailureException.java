package com.silentcloud.springrest.service.api;

import cn.hutool.core.util.StrUtil;
import cn.hutool.http.HttpStatus;

/**
 * 已逻辑删除资源操作异常
 *
 * @author bianyun
 */
public class LogicallyDeletedEntityActivateFailureException extends AbstractServiceLayerException {
    private static final long serialVersionUID = 3217909287389913650L;
    private static final int HTTP_STATUS_CODE = HttpStatus.HTTP_FORBIDDEN;
    private static final String ERROR_TEMPLATE = "资源{}异常";
    private static final String MSG_TEMPLATE = "{}[id={}] 已被逻辑删除, 无法{}";

    public LogicallyDeletedEntityActivateFailureException(String label, Object id, String actionLabel) {
        super(HTTP_STATUS_CODE, StrUtil.format(ERROR_TEMPLATE, actionLabel),
                StrUtil.format(MSG_TEMPLATE, label, id, actionLabel));
    }

}
