package com.silentcloud.springrest.web;

import cn.hutool.core.lang.Assert;
import cn.hutool.core.util.ReflectUtil;
import cn.hutool.core.util.StrUtil;
import com.silentcloud.springrest.common.ErrorMsg;
import com.silentcloud.springrest.service.api.CustomServiceLayerException;
import com.silentcloud.springrest.util.LabelUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.lang.reflect.Field;
import java.time.LocalDateTime;

@Slf4j
@ControllerAdvice(annotations = RestController.class)
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(CustomWebLayerException.class)
    public ResponseEntity<Object> handleCustomWebLayerException(CustomWebLayerException ex) {
        return new ResponseEntity<>(ex.getErrorMsg(), ex.getHttpStatus());
    }

    @ExceptionHandler(CustomServiceLayerException.class)
    public ResponseEntity<Object> handleCustomServiceLayerException(CustomServiceLayerException ex) {
        return new ResponseEntity<>(ex.getErrorMsg(), HttpStatus.valueOf(ex.getHttpStatusCode()));
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<Object> handleIllegalArgumentException(IllegalArgumentException ex) {
        ErrorMsg errorMsg = buildErrorMsg("参数不合法", ex);
        return new ResponseEntity<>(errorMsg, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> globleExcpetionHandler(Exception ex) {
        ErrorMsg errorMsg = buildErrorMsg("服务器内部错误", ex);
        log.error("服务器内部错误", ex);
        return new ResponseEntity<>(errorMsg, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Override
    protected @NonNull ResponseEntity<Object> handleMethodArgumentNotValid(@NonNull MethodArgumentNotValidException ex,
                                                                           @NonNull HttpHeaders headers,
                                                                           @NonNull HttpStatus status,
                                                                           @NonNull WebRequest request) {
        BindingResult bindingResult = ex.getBindingResult();

        String detailMessage = parseFieldErrorMessage(bindingResult);

        ErrorMsg errorMsg = ErrorMsg.of(LocalDateTime.now(), "对象参数不合法", detailMessage);
        return new ResponseEntity<>(errorMsg, HttpStatus.BAD_REQUEST);
    }

    private static String parseFieldErrorMessage(BindingResult bindingResult) {
        FieldError firstFieldError = bindingResult.getFieldError();

        if (firstFieldError != null) {
            String firstErrorFieldStr = firstFieldError.getField();

            if (firstErrorFieldStr.contains(".")) {
                Class<?> actualClassType = bindingResult.getFieldType(StrUtil.subBefore(firstErrorFieldStr, ".", true));
                if (actualClassType != null) {
                    String fieldName = StrUtil.subBefore(StrUtil.subAfter(firstErrorFieldStr, ".", true), "[", true);
                    Field field = ReflectUtil.getField(actualClassType, fieldName);
                    Assert.notNull(field, "类{}中不存在字段{}", actualClassType.getName(), fieldName);
                    String fieldFullLabel = LabelUtil.getFieldFullLabel(actualClassType, field);
                    return fieldFullLabel + firstFieldError.getDefaultMessage();
                } else {
                    return "";
                }
            } else {
                Object bindingTarget = bindingResult.getTarget();
                if (bindingTarget != null) {
                    Class<?> bindingTargetClass = bindingTarget.getClass();
                    Field field = ReflectUtil.getField(bindingTargetClass, firstErrorFieldStr);
                    String fieldFullLabel = LabelUtil.getFieldFullLabel(bindingTargetClass, field);
                    return fieldFullLabel + firstFieldError.getDefaultMessage();
                } else {
                    return firstFieldError.getDefaultMessage();
                }
            }
        } else {
            return bindingResult.getAllErrors().stream().findFirst().map(ObjectError::getDefaultMessage).orElse("");
        }
    }

    private ErrorMsg buildErrorMsg(String error, Throwable t) {
        Throwable cause = t.getCause();
        String message = cause != null ? cause.getMessage() : t.getMessage();
        return ErrorMsg.of(LocalDateTime.now(), error, message);
    }

}
