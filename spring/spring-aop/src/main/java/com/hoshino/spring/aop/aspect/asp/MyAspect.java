package com.hoshino.spring.aop.aspect.asp;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;

/**
 * @author Yy_hoshino
 * @date 2021-04-10 13:52
 */
public class MyAspect implements MethodInterceptor {

    public void checkPermissions() {
        System.out.println("模拟检查权限...");
    }

    public void log() {
        System.out.println("模拟记录日志...");
    }

    @Override
    public Object invoke(MethodInvocation methodInvocation) throws Throwable {
        checkPermissions();
        // 执行目标方法
        Object obj = methodInvocation.proceed();
        log();
        return obj;
    }

}
