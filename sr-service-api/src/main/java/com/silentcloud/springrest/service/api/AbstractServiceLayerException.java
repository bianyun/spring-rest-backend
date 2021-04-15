package com.silentcloud.springrest.service.api;

import com.silentcloud.springrest.common.ErrorMsg;

import java.time.LocalDateTime;

/**
 * 服务层异常抽象父类（所有自定义服务层异常应继承此类）
 *
 * @author bianyun
 */
public abstract class AbstractServiceLayerException extends RuntimeException {
    private final int httpStatusCode;
    private final ErrorMsg errorMsg;

    protected AbstractServiceLayerException(int httpStatusCode, String error, String message) {
        super(error + ": " + message);
        this.httpStatusCode = httpStatusCode;
        this.errorMsg = ErrorMsg.of(LocalDateTime.now(), error, message);
    }

    protected AbstractServiceLayerException(int httpStatusCode, String error, String message, Throwable cause) {
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
