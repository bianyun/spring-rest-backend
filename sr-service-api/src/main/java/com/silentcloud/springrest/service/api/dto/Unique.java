package com.silentcloud.springrest.service.api.dto;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * 用于提示 Service层在做新增和修改时，需要对哪些属性的值做唯一性校验
 * （一般对应于 Entity中 带有 @Column(unqiue = true) 注解的属性）
 * <p>
 * 注： 此注解用在DTO对象的属性上，只对部分基本类型(String, Integer, Long)的属性做唯一性校验，其它类型会被忽略
 */
@Target(FIELD)
@Retention(RUNTIME)
public @interface Unique {

    /**
     * 检查唯一性的范围（引用对象内另一个属性的名称，即在查询时会加上所引用的属性的值）
     */
    String scope() default "";

    /**
     * Entity中对应的属性名, 如果 DTO对象中的属性名与 Entity中的属性名一致，则不用设置此属性
     */
    String entityAttribute() default "";
}
