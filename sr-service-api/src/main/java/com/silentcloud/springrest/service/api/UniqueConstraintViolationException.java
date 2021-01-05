package com.silentcloud.springrest.service.api;

import cn.hutool.core.util.StrUtil;
import cn.hutool.http.HttpStatus;

public class UniqueConstraintViolationException extends CustomServiceLayerException {
    private static final long serialVersionUID = -2994165153936345144L;
    private static final int httpStatusCode = HttpStatus.HTTP_CONFLICT;
    private static final String ERROR = "对象属性值不唯一";
    private static final String MSG_TEMPLATE = "{} [{}] 已被使用";

    public UniqueConstraintViolationException(String attrLabel, Object attrValue) {
        super(httpStatusCode, ERROR, StrUtil.format(MSG_TEMPLATE, attrLabel, attrValue));
    }

}
