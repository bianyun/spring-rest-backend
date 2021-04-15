package com.silentcloud.springrest.service.api;

import cn.hutool.core.util.StrUtil;
import cn.hutool.http.HttpStatus;

/**
 * 违反唯一约束异常
 *
 * @author bianyun
 */
public class UniqueConstraintViolationException extends AbstractServiceLayerException {
    private static final long serialVersionUID = -2994165153936345144L;
    private static final int HTTP_STATUS_CODE = HttpStatus.HTTP_CONFLICT;
    private static final String ERROR = "对象属性值不唯一";
    private static final String MSG_TEMPLATE = "{} [{}] 已被使用";

    public UniqueConstraintViolationException(String attrLabel, Object attrValue) {
        super(HTTP_STATUS_CODE, ERROR, StrUtil.format(MSG_TEMPLATE, attrLabel, attrValue));
    }

}
