package com.silentcloud.springrest.service.impl.util;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

/**
 * Bean工具组件（用于从Spring上下文根据 Class 直接获取 Bean）
 *
 * @author bianyun
 */
@Component
public class BeanUtil implements ApplicationContextAware {

    @Nullable
    private static ApplicationContext context;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        context = applicationContext;
    }

    public static <T> T getBean(Class<T> beanClass) {
        assert context != null;
        return context.getBean(beanClass);
    }
}
