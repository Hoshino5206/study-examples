package com.hoshino.springboot.annotation.aspect;

import com.hoshino.springboot.annotation.annotation.OperationLog;
import com.hoshino.springboot.annotation.util.LogUtil;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

/**
 * @author huangyuehao
 * @date 2023-04-06
 */
@Aspect
@Order(1)
@Component
@Slf4j
public class OperationLogAspect {

    /**
     * 切面获取注解方式1
     *
     * @param joinPoint
     * @param operationLog
     * @return
     * @throws Throwable
     */
    @Around("@annotation(operationLog)")
    public Object around(ProceedingJoinPoint joinPoint, OperationLog operationLog) throws Throwable {
        LogUtil.logPrintOut(operationLog);

        Object obj = null;
        try {
            obj = joinPoint.proceed();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return obj;
    }

    @Pointcut("execution(public * com.hoshino.springboot.annotation.*.*(..))")
    public void pointCut() {}

    /**
     * 切面获取注解方式2
     *
     * @param joinPoint
     */
    @After("pointCut()")
    public void after(JoinPoint joinPoint){
        Signature signature = joinPoint.getSignature();
        MethodSignature methodSignature = (MethodSignature) signature;
        Method method = methodSignature.getMethod();
        OperationLog logging = method.getAnnotation(OperationLog.class);
        LogUtil.logPrintOut(logging);
    }

}
