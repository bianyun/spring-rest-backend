package com.silentcloud.springrest.web;

import com.silentcloud.springrest.common.ErrorMsg;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

/**
 * WEB层异常抽象父类（所有自定义 Web层异常应继承此类）
 *
 * @author bianyun
 */
public abstract class AbstractWebLayerException extends RuntimeException {
    private final HttpStatus httpStatus;
    private final ErrorMsg errorMsg;

    protected AbstractWebLayerException(HttpStatus httpStatus, String error, String message) {
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
