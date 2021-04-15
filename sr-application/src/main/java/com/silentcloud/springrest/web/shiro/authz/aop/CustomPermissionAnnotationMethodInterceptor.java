package com.silentcloud.springrest.web.shiro.authz.aop;

import com.silentcloud.springrest.util.MiscUtil;
import org.apache.shiro.aop.AnnotationResolver;
import org.apache.shiro.aop.MethodInvocation;
import org.apache.shiro.authz.AuthorizationException;
import org.apache.shiro.authz.aop.AuthorizingAnnotationMethodInterceptor;


/**
 * 自定义权限注解方法拦截器
 *
 * @author bianyun
 */
public class CustomPermissionAnnotationMethodInterceptor extends AuthorizingAnnotationMethodInterceptor {

    public CustomPermissionAnnotationMethodInterceptor() {
        super(new CustomPermissionAnnotationHandler());
    }

    public CustomPermissionAnnotationMethodInterceptor(AnnotationResolver resolver) {
        super(new CustomPermissionAnnotationHandler(), resolver);
    }

    @Override
    public void assertAuthorized(MethodInvocation mi) throws AuthorizationException {
        CustomPermissionAnnotationHandler handler = (CustomPermissionAnnotationHandler) getHandler();

        String domain = MiscUtil.parseDomainOfControllerClass(mi.getThis().getClass());
        handler.setApiPermDomain(domain);
        handler.assertAuthorized(getAnnotation(mi));
    }
}
