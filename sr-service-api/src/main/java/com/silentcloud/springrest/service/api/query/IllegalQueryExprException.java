package com.silentcloud.springrest.service.api.query;

import cn.hutool.core.util.StrUtil;
import cn.hutool.http.HttpStatus;
import com.silentcloud.springrest.service.api.AbstractServiceLayerException;

/**
 * 非法查询参数异常
 *
 * @author bianyun
 */
public class IllegalQueryExprException extends AbstractServiceLayerException {
    private static final long serialVersionUID = -5159940254377191475L;
    private static final int HTTP_STATUS_CODE = HttpStatus.HTTP_BAD_REQUEST;
    private static final String ERROR = "查询参数不合法";
    private static final String MSG_TEMPLATE = "{}查询表达式不合法: {}";
    private static final String MSG_TEMPLATE_DETAIL = "{}查询表达式不合法: 非法的{}[{}]";

    public IllegalQueryExprException(String queryType, String description) {
        super(HTTP_STATUS_CODE, ERROR, StrUtil.format(MSG_TEMPLATE, queryType, description));
    }

    public IllegalQueryExprException(String queryType, ErrorType errorType, String errorValue) {
        super(HTTP_STATUS_CODE, ERROR, StrUtil.format(MSG_TEMPLATE_DETAIL, queryType, errorType.getTypeName(), errorValue));
    }

    @SuppressWarnings("AlibabaEnumConstantsMustHaveComment")
    public enum ErrorType {
        FIELD("字段名"), OPERATOR("操作符");

        private final String typeName;

        ErrorType(String typeName) {
            this.typeName = typeName;
        }

        public String getTypeName() {
            return typeName;
        }
    }
}
