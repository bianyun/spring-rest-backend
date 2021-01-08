package com.silentcloud.springrest.web.shiro.authz.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import static com.silentcloud.springrest.web.util.Consts.API_PERM_PREFIX;
import static com.silentcloud.springrest.web.util.Consts.PLACEHOLDER_LABEL;

@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@RequiresPerm(name = "查看" + PLACEHOLDER_LABEL + "列表", value = API_PERM_PREFIX + "view-list")
public @interface RequiresPermViewList {

}
