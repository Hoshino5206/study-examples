package com.hoshino.springboot.aop.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;

/**
 * @author huangyuehao
 * @date 2022-12-14
 */
@Aspect
@Order(5)
@Component
@Slf4j
public class ControllerLogAspect {

    @Pointcut("execution(public * com.hoshino.springboot.aop.controller.*.*(..))")
    public void pointCut() {}

    // 前置通知，在目标方法之前执行
    @Before("pointCut()")
    public void deBefore(JoinPoint joinPoint){
        // 接收到请求，记录请求内容
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        // 记录下请求内容
        System.out.println("URL : " + request.getRequestURL().toString());
        System.out.println("HTTP_METHOD : " + request.getMethod());
        System.out.println("IP : " + request.getRemoteAddr());
        System.out.println("CLASS_METHOD : " + joinPoint.getSignature().getDeclaringTypeName() + "." + joinPoint.getSignature().getName());
        System.out.println("ARGS : " + Arrays.toString(joinPoint.getArgs()));

    }

    // 环绕通知，环绕增强，相当于MethodInterceptor
    @Around("pointCut()")
    public Object around(ProceedingJoinPoint joinPoint) throws Throwable {
        Object obj = null;
        try {
            obj = joinPoint.proceed();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return obj;
    }

    // 后置通知
    @AfterReturning(returning = "ret", pointcut = "pointCut()")
    public void doAfterReturning(Object ret) throws Throwable {
        // 处理完请求，返回内容
        System.out.println("方法的返回值 : " + ret);
    }

    // 后置异常通知
    @AfterThrowing("pointCut()")
    public void afterThrowing(JoinPoint joinPoint){
        System.out.println("方法异常时执行.....");
    }

    // 后置最终通知，final增强，不管是抛出异常或者正常退出都会执行
    @After("pointCut()")
    public void after(JoinPoint joinPoint){
        System.out.println("方法最后执行.....");
    }



}
