package com.paladin.qos.core;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.SimplePrincipalCollection;
import org.apache.shiro.subject.support.DelegatingSubject;
import org.apache.shiro.web.filter.PathMatchingFilter;
import org.apache.shiro.web.subject.support.WebDelegatingSubject;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * @author TontoZhou
 * @since 2019/11/29
 */
public class QosAnonymousFilter extends PathMatchingFilter {

    private static final String RUN_AS_PRINCIPALS_SESSION_KEY =
            DelegatingSubject.class.getName() + ".RUN_AS_PRINCIPALS_SESSION_KEY";

    @Override
    protected boolean onPreHandle(ServletRequest request, ServletResponse response, Object mappedValue) {
        WebDelegatingSubject subject = (WebDelegatingSubject) SecurityUtils.getSubject();
        QosUserSession userSession = (QosUserSession) subject.getPrincipal();

        if (userSession == null) {
            userSession = new QosUserSession("DongRuanZongGuan", "东软综管用户", "DongRuanZongGuan");
            userSession.isSystemAdmin = true;
            userSession.roleLevel = QosUserSession.ROLE_LEVEL_ADMIN;
        }

        List<Object> principals = Arrays.asList(userSession);
        PrincipalCollection principalCollection = new SimplePrincipalCollection(principals, "DONGRUAN");
        List<PrincipalCollection> stack = new CopyOnWriteArrayList<>();
        stack.add(0, principalCollection);
        Session session = subject.getSession();
        session.setAttribute(RUN_AS_PRINCIPALS_SESSION_KEY, stack);
        return true;
    }

}
