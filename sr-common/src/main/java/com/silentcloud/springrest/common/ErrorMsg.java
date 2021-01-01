package com.silentcloud.springrest.common;

import lombok.Value;

import java.time.LocalDateTime;

@Value(staticConstructor = "of")
public class ErrorMsg {
    LocalDateTime timestamp;
    String error;
    String message;
}
