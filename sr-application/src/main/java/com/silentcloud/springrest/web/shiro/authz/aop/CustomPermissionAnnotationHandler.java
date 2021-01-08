package com.silentcloud.springrest.web.shiro.authz.aop;

import com.silentcloud.springrest.web.shiro.authz.annotation.RequiresPerm;
import com.silentcloud.springrest.web.util.Consts;
import org.apache.shiro.authz.AuthorizationException;
import org.apache.shiro.authz.aop.AuthorizingAnnotationHandler;
import org.apache.shiro.subject.Subject;

import java.lang.annotation.Annotation;

public class CustomPermissionAnnotationHandler extends AuthorizingAnnotationHandler {
    private final ThreadLocal<String> apiPermDomain = new ThreadLocal<>();

    public void setApiPermDomain(String domain) {
        apiPermDomain.set(domain);
    }

    public CustomPermissionAnnotationHandler() {
        super(RequiresPerm.class);
    }

    @Override
    public void assertAuthorized(Annotation a) throws AuthorizationException {
        if (!(a instanceof RequiresPerm)) return;

        RequiresPerm rpAnnotation = (RequiresPerm) a;
        String perm = rpAnnotation.value().replace(Consts.PLACEHOLDER_DOMAIN, apiPermDomain.get());

        Subject subject = getSubject();
        subject.checkPermission(perm);
    }

}
