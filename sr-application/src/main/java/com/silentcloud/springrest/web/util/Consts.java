package com.silentcloud.springrest.web.util;

import cn.hutool.core.util.StrUtil;
import com.silentcloud.springrest.SpringrestApplication;

public abstract class Consts {

    public static final String PLACEHOLDER_DOMAIN = "{domain}";

    public static final String PLACEHOLDER_LABEL = "{label}";

    public static final String API_PERM_PREFIX = "api:" + PLACEHOLDER_DOMAIN + ":";

    public static final String PACKAGE_OR_CLASS_LEVEL_API_PERM_TEMPLATE = StrUtil.removeSuffix(API_PERM_PREFIX, ":");

    public static final int SUBCLASS_API_OPERATION_ORDER_OFFSET = 50;

    public static final String APP_ROOT_PACKAGE_NAME = SpringrestApplication.class.getPackage().getName();

}
