package com.silentcloud.springrest.service.api;

import cn.hutool.http.HttpStatus;

/**
 * 非法操作异常
 *
 * @author bianyun
 */
public class IllegalServiceOperationException extends AbstractServiceLayerException {
    private static final long serialVersionUID = 8909259424665511377L;

    private static final int HTTP_STATUS_CODE = HttpStatus.HTTP_INTERNAL_ERROR;
    private static final String ERROR = "非法操作异常";

    public IllegalServiceOperationException(String msg) {
        super(HTTP_STATUS_CODE, ERROR, msg);
    }
}
