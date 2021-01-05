package com.silentcloud.springrest.service.api;

import com.silentcloud.springrest.common.ErrorMsg;

import java.time.LocalDateTime;

public abstract class CustomServiceLayerException extends RuntimeException {
    private final int httpStatusCode;
    private final ErrorMsg errorMsg;

    protected CustomServiceLayerException(int httpStatusCode, String error, String message) {
        super(error + ": " + message);
        this.httpStatusCode = httpStatusCode;
        this.errorMsg = ErrorMsg.of(LocalDateTime.now(), error, message);
    }

    protected CustomServiceLayerException(int httpStatusCode, String error, String message, Throwable cause) {
        super(error + ": " + message, cause);
        this.httpStatusCode = httpStatusCode;
        this.errorMsg = ErrorMsg.of(LocalDateTime.now(), error, message);
    }

    public int getHttpStatusCode() {
        return httpStatusCode;
    }

    public ErrorMsg getErrorMsg() {
        return errorMsg;
    }
}
