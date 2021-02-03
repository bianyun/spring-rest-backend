package com.silentcloud.springrest.service.api;

import cn.hutool.http.HttpStatus;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class EntityBatchDeleteFailureException extends CustomServiceLayerException {
    private static final long serialVersionUID = 5875193542560091333L;
    private static final int httpStatusCode = HttpStatus.HTTP_CONFLICT;
    private static final String ERROR = "资源批量删除异常";

    @SuppressWarnings("unused")
    public EntityBatchDeleteFailureException(String label, Map<String, List<Object>> exceptionMap) {
        super(httpStatusCode, ERROR, buildErrorMsg(exceptionMap));
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
