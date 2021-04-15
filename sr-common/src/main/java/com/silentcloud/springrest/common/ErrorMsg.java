package com.silentcloud.springrest.common;

import lombok.Value;

import java.time.LocalDateTime;

/**
 * 错误消息
 *
 * @author bianyun
 */
@Value(staticConstructor = "of")
public class ErrorMsg {
    LocalDateTime timestamp;
    String error;
    String message;
}
