package com.silentcloud.springrest.service.api;


import cn.hutool.http.HttpStatus;

public class IllegalServiceOperationException extends CustomServiceLayerException {
    private static final long serialVersionUID = 8909259424665511377L;

    private static final int httpStatusCode = HttpStatus.HTTP_INTERNAL_ERROR;
    private static final String ERROR = "非法操作异常";

    public IllegalServiceOperationException(String msg) {
        super(httpStatusCode, ERROR, msg);
    }
}
