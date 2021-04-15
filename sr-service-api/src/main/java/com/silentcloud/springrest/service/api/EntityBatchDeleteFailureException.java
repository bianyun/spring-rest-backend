package com.silentcloud.springrest.service.api;

import cn.hutool.http.HttpStatus;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 资源批量删除异常
 *
 * @author bianyun
 */
public class EntityBatchDeleteFailureException extends AbstractServiceLayerException {
    private static final long serialVersionUID = 5875193542560091333L;
    private static final int HTTP_STATUS_CODE = HttpStatus.HTTP_CONFLICT;
    private static final String ERROR = "资源批量删除异常";

    @SuppressWarnings("unused")
    public EntityBatchDeleteFailureException(String label, Map<String, List<Object>> exceptionMap) {
        super(HTTP_STATUS_CODE, ERROR, buildErrorMsg(exceptionMap));
    }

    private static String buildErrorMsg(Map<String, List<Object>> exceptionMap) {
        StringBuilder sb = new StringBuilder();
        exceptionMap.forEach((key, value) -> {
            String idsStr = value.stream().map(Object::toString).collect(Collectors.joining(", "));
            sb.append(key).append(": ID (").append(idsStr).append("); ");
        });
        return sb.toString();
    }
}
