package com.silentcloud.springrest.web;

import org.springframework.http.HttpStatus;

public class IllegalWebOperationException extends CustomWebLayerException {
    private static final long serialVersionUID = 6655293530254875372L;

    private static final HttpStatus HTTP_STATUS = HttpStatus.INTERNAL_SERVER_ERROR;
    private static final String ERROR = "非法操作异常";

    public IllegalWebOperationException(String msg) {
        super(HTTP_STATUS, ERROR, msg);
    }
}
