package com.silentcloud.springrest.web.shiro.intercepter;

import com.silentcloud.springrest.web.shiro.authz.annotation.RequiresPerm;
import com.silentcloud.springrest.web.shiro.authz.annotation.RequiresPermViewDetail;
import com.silentcloud.springrest.web.shiro.authz.annotation.RequiresPermViewList;
import com.silentcloud.springrest.web.shiro.authz.aop.CustomPermissionAnnotationMethodInterceptor;
import org.apache.shiro.authz.annotation.*;
import org.apache.shiro.spring.aop.SpringAnnotationResolver;
import org.apache.shiro.spring.security.interceptor.AopAllianceAnnotationsAuthorizingMethodInterceptor;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.springframework.core.annotation.AnnotationUtils;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

/**
 * 自定义 AuthorizationAttributeSourceAdvisor
 *
 * @author bianyun
 */
@SuppressWarnings("unchecked")
public class CustomAuthorizationAttributeSourceAdvisor extends AuthorizationAttributeSourceAdvisor {

    private static final Class<? extends Annotation>[] AUTHZ_ANNOTATION_CLASSES =
            new Class[]{
                    RequiresAuthentication.class,
                    RequiresUser.class,
                    RequiresGuest.class,
                    RequiresRoles.class,
                    RequiresPermissions.class,
                    RequiresPerm.class,
                    RequiresPermViewList.class,
                    RequiresPermViewDetail.class
            };

    public CustomAuthorizationAttributeSourceAdvisor() {
        AopAllianceAnnotationsAuthorizingMethodInterceptor interceptor =
                new AopAllianceAnnotationsAuthorizingMethodInterceptor();
        interceptor.getMethodInterceptors().add(new CustomPermissionAnnotationMethodInterceptor(new SpringAnnotationResolver()));
        setAdvice(interceptor);
    }

    /**
     * Returns <tt>true</tt> if the method or the class has any Shiro annotations, false otherwise.
     * The annotations inspected are:
     * <ul>
     * <li>{@link org.apache.shiro.authz.annotation.RequiresAuthentication RequiresAuthentication}</li>
     * <li>{@link org.apache.shiro.authz.annotation.RequiresUser RequiresUser}</li>
     * <li>{@link org.apache.shiro.authz.annotation.RequiresGuest RequiresGuest}</li>
     * <li>{@link org.apache.shiro.authz.annotation.RequiresRoles RequiresRoles}</li>
     * <li>{@link org.apache.shiro.authz.annotation.RequiresPermissions RequiresPermissions}</li>
     * <li>{@link com.silentcloud.springrest.web.shiro.authz.annotation.RequiresPerm RequiresPerm}</li>
     * </ul>
     *
     * @param method      the method to check for a Shiro annotation
     * @param targetClass the class potentially declaring Shiro annotations
     * @return <tt>true</tt> if the method has a Shiro annotation, false otherwise.
     * @see org.springframework.aop.MethodMatcher#matches(java.lang.reflect.Method, Class)
     */
    @Override
    public boolean matches(Method method, Class targetClass) {
        Method m = method;

        if (isAuthzAnnotationPresent(m)) {
            return true;
        }

        //The 'method' parameter could be from an interface that doesn't have the annotation.
        //Check to see if the implementation has it.
        try {
            m = targetClass.getMethod(m.getName(), m.getParameterTypes());
            return isAuthzAnnotationPresent(m) || isAuthzAnnotationPresent(targetClass);
        } catch (NoSuchMethodException ignored) {
            //default return value is false.  If we can't find the method, then obviously
            //there is no annotation, so just use the default return value.
        }

        return false;
    }

    private boolean isAuthzAnnotationPresent(Class<?> targetClazz) {
        for (Class<? extends Annotation> annClass : AUTHZ_ANNOTATION_CLASSES) {
            Annotation a = AnnotationUtils.findAnnotation(targetClazz, annClass);
            if (a != null) {
                return true;
            }
        }
        return false;
    }

    private boolean isAuthzAnnotationPresent(Method method) {
        for (Class<? extends Annotation> annClass : AUTHZ_ANNOTATION_CLASSES) {
            Annotation a = AnnotationUtils.findAnnotation(method, annClass);
            if (a != null) {
                return true;
            }
        }
        return false;
    }
}
