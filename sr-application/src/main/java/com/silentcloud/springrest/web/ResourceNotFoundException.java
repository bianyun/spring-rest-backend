package com.silentcloud.springrest.web;

import cn.hutool.core.util.StrUtil;
import org.springframework.http.HttpStatus;

/**
 * 资源不存在异常
 *
 * @author bianyun
 */
public class ResourceNotFoundException extends AbstractWebLayerException {
    private static final long serialVersionUID = 5136474288616839120L;

    private static final HttpStatus HTTP_STATUS = HttpStatus.NOT_FOUND;
    private static final String ERROR = "获取资源异常";
    private static final String MSG_TEMPLATE = "{}[id={}] 不存在";

    public ResourceNotFoundException(Object id, String resourceLabel) {
        super(HTTP_STATUS, ERROR, StrUtil.format(MSG_TEMPLATE, resourceLabel, id));
    }
}
