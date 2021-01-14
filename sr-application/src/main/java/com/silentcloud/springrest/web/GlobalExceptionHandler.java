package com.silentcloud.springrest.web;

import cn.hutool.core.lang.Assert;
import cn.hutool.core.util.ReUtil;
import cn.hutool.core.util.ReflectUtil;
import cn.hutool.core.util.StrUtil;
import com.silentcloud.springrest.common.ErrorMsg;
import com.silentcloud.springrest.service.api.CustomServiceLayerException;
import com.silentcloud.springrest.util.LabelUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authz.AuthorizationException;
import org.apache.shiro.authz.UnauthenticatedException;
import org.apache.shiro.authz.UnauthorizedException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

import static com.silentcloud.springrest.web.controller.sys.PermController.API_PERM_VALUE_NAME_MAP;

@Slf4j
@ControllerAdvice(annotations = RestController.class)
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {
    private static final String REGEX_API_PERM_UNAUTHORIZED_MSG = "User is not permitted \\[(\\S+)\\]";

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

    @ExceptionHandler(IncorrectCredentialsException.class)
    public ResponseEntity<Object> handleIncorrectCredentialsException() {
        ErrorMsg errorMsg = buildErrorMsg("用户认证异常", "用户输入的账号或密码不正确");
        return new ResponseEntity<>(errorMsg, HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(AuthenticationException.class)
    public ResponseEntity<Object> handleAuthenticationException(AuthenticationException ex) {
        ErrorMsg errorMsg = buildErrorMsg("用户认证异常", ex);
        log.error("用户认证异常", ex);
        return new ResponseEntity<>(errorMsg, HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(UnauthenticatedException.class)
    public ResponseEntity<Object> handleUnauthenticatedException(UnauthenticatedException ex) {
        ErrorMsg errorMsg = buildErrorMsg("用户认证异常", ex);
        return new ResponseEntity<>(errorMsg, HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(UnauthorizedException.class)
    public ResponseEntity<Object> handleUnauthorizedException(UnauthorizedException ex) {
        String msg = ex.getMessage();
        ErrorMsg errorMsg;
        if (ReUtil.isMatch(REGEX_API_PERM_UNAUTHORIZED_MSG, msg)) {
            String permValue = ReUtil.getGroup1(REGEX_API_PERM_UNAUTHORIZED_MSG, msg);
            String permDesc = API_PERM_VALUE_NAME_MAP.getOrDefault(permValue, permValue);
            errorMsg = buildErrorMsg("用户鉴权异常", StrUtil.format("用户没有权限访问接口 [{}]", permDesc));
        } else {
            errorMsg = buildErrorMsg("用户鉴权异常", ex);
        }
        return new ResponseEntity<>(errorMsg, HttpStatus.FORBIDDEN);
    }

    @ExceptionHandler(AuthorizationException.class)
    public ResponseEntity<Object> handleAuthorizationException(AuthorizationException ex) {
        ErrorMsg errorMsg = buildErrorMsg("用户鉴权异常", ex);
        return new ResponseEntity<>(errorMsg, HttpStatus.FORBIDDEN);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> globleExcpetionHandler(Exception ex) {
        ErrorMsg errorMsg = buildErrorMsg("服务器内部错误", ex);
        log.error("服务器内部错误", ex);
        return new ResponseEntity<>(errorMsg, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
                                                                           HttpHeaders headers,
                                                                           HttpStatus status,
                                                                           WebRequest request) {
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
                    return StrUtil.nullToDefault(firstFieldError.getDefaultMessage(), "");
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

    private ErrorMsg buildErrorMsg(String error, String message) {
        return ErrorMsg.of(LocalDateTime.now(), error, message);
    }
}
