package com.hoshino.spring.aop.aspectJ_anno.asp;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

/**
 * @author Yy_hoshino
 * @date 2021-04-10 14:03
 */
@Component("myAspect")
@Aspect //标注当前myAspect是一个切面类
public class MyAspect {

    // 定义切入点表达式
    @Pointcut("execution(* com.hoshino.spring.aop.aspectJ_anno.dao.impl.*.*(..))")
    // 使用一个返回值为 void 、方法体为空的方法来命名切入点
    private void myPointCut(){}

    @Before("execution(* com.hoshino.spring.aop.aspectJ_anno.dao.impl.UserDaoImpl.save())")
    public void myBefore(JoinPoint joinPoint) {
        System.out.println("前置通知......");
    }

    @AfterReturning("execution(* com.hoshino.spring.aop.aspectJ_anno.dao.impl.UserDaoImpl.delete())")
    public void myAfterReturning(JoinPoint joinPoint) {
        System.out.println("后置通知......");
    }

    @Around("execution(* com.hoshino.spring.aop.aspectJ_anno.dao.impl.UserDaoImpl.update())")
    public Object myAround(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        System.out.println("环绕前通知......");
        Object obj = proceedingJoinPoint.proceed();
        System.out.println("环绕后通知......");
        return obj;
    }

    @AfterThrowing(value = "execution(* com.hoshino.spring.aop.aspectJ_anno.dao.impl.UserDaoImpl.find())", throwing = "ex")
    public void myAfterThrowing(JoinPoint joinPoint, Throwable ex) {
        System.out.println("异常抛出通知......"+ex.getMessage());
    }

    @After("myPointCut()")
    public void myAfter() {
        System.out.println("最终通知......");
    }

}
