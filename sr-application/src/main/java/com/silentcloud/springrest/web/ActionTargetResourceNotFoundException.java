package com.silentcloud.springrest.web;

import cn.hutool.core.util.StrUtil;
import org.springframework.http.HttpStatus;

/**
 * 资源操作异常
 *
 * @author bianyun
 */
public class ActionTargetResourceNotFoundException extends AbstractWebLayerException {
    private static final long serialVersionUID = 6655293530254875372L;

    private static final HttpStatus HTTP_STATUS = HttpStatus.BAD_REQUEST;
    private static final String ERROR = "资源操作异常";
    private static final String MSG_TEMPLATE = "所{}的{}[id={}] 不存在";

    public ActionTargetResourceNotFoundException(Object id, String resourceLabel, String actionLabel) {
        super(HTTP_STATUS, ERROR, StrUtil.format(MSG_TEMPLATE, actionLabel, resourceLabel, id));
    }
}
