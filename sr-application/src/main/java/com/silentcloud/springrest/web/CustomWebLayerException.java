package com.silentcloud.springrest.web;

import com.silentcloud.springrest.common.ErrorMsg;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

public abstract class CustomWebLayerException extends RuntimeException {
    private final HttpStatus httpStatus;
    private final ErrorMsg errorMsg;

    protected CustomWebLayerException(HttpStatus httpStatus, String error, String message) {
        this.httpStatus = httpStatus;
        this.errorMsg = ErrorMsg.of(LocalDateTime.now(), error, message);
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }

    public ErrorMsg getErrorMsg() {
        return errorMsg;
    }
}
