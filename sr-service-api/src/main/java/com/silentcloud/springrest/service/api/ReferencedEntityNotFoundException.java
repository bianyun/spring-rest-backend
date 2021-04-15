package com.silentcloud.springrest.service.api;

import cn.hutool.core.util.StrUtil;
import cn.hutool.http.HttpStatus;

/**
 * 实体引用异常
 *
 * @author bianyun
 */
public class ReferencedEntityNotFoundException extends AbstractServiceLayerException {
    private static final long serialVersionUID = -5753742098230419161L;
    private static final int HTTP_STATUS_CODE = HttpStatus.HTTP_BAD_REQUEST;
    private static final String MSG_TEMPLATE = "所引用的 {}[id={}] 不存在";

    public ReferencedEntityNotFoundException(String label, Object id) {
        super(HTTP_STATUS_CODE, "实体引用异常", StrUtil.format(MSG_TEMPLATE, label, id));
    }

}
