package com.hoshino.spring.aop.aspectJ_xml.asp;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;

/**
 * @author Yy_hoshino
 * @date 2021-04-10 14:03
 */
public class MyAspect {

    public void myBefore(JoinPoint joinPoint) {
        System.out.println("前置通知......");
    }

    public void myAfterReturning(JoinPoint joinPoint) {
        System.out.println("后置通知......");
    }

    public Object myAround(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        System.out.println("环绕前通知......");
        Object obj = proceedingJoinPoint.proceed();
        System.out.println("环绕后通知......");
        return obj;
    }

    public void myAfterThrowing(JoinPoint joinPoint, Throwable ex) {
        System.out.println("异常抛出通知......"+ex.getMessage());
    }

    public void myAfter() {
        System.out.println("最终通知......");
    }

}
