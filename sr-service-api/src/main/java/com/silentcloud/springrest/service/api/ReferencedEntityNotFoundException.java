package com.silentcloud.springrest.service.api;

import cn.hutool.core.util.StrUtil;
import cn.hutool.http.HttpStatus;

public class ReferencedEntityNotFoundException extends CustomServiceLayerException {
    private static final long serialVersionUID = -5753742098230419161L;
    private static final int httpStatusCode = HttpStatus.HTTP_BAD_REQUEST;
    private static final String MSG_TEMPLATE = "所引用的 {}[id={}] 不存在";

    public ReferencedEntityNotFoundException(String label, Object id) {
        super(httpStatusCode, "实体引用异常", StrUtil.format(MSG_TEMPLATE, label, id));
    }

}
