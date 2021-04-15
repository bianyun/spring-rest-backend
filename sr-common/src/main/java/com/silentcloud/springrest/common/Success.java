package com.silentcloud.springrest.common;

import lombok.Data;

import java.time.LocalDateTime;

/**
 * 用户登录成功响应
 *
 * @author bianyun
 */
@Data
public class Success {
    private LocalDateTime timestamp = LocalDateTime.now();
    private String message;
    private String token;

    public static Success of(String message) {
        Success result = new Success();
        result.setMessage(message);
        return result;
    }

    public static Success of(String message, String token) {
        Success result = new Success();
        result.setMessage(message);
        result.setToken(token);
        return result;
    }
}
