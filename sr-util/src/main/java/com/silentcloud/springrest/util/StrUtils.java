package com.silentcloud.springrest.util;


import cn.hutool.core.util.StrUtil;

/**
 * 字符串工具类
 *
 * @author bianyun
 */
@SuppressWarnings("unused")
public final class StrUtils extends StrUtil {
    private StrUtils() {
        MiscUtil.deliberatelyProhibitInstantiation();
    }

    public static final String SINGLE_QUOTE = "'";
    public static final String DOUBLE_QUOTE = "\"";
    public static final String TRUE = "true";
    public static final String FALSE = "false";
    public static final String NULL = "null";

    public static final String PARENTHESES = "()";
    public static final String OPENING_PARENTHESIS = "(";
    public static final String CLOSING_PARENTHESIS = ")";

    public static final String BRACES = "{}";
    public static final String OPENING_BRACE = "{";
    public static final String CLOSING_BRACE = "}";

    public static final String BRACKETS = "[]";
    public static final String OPENING_BRACKET = "[";
    public static final String CLOSING_BRACKET = "]";
}
