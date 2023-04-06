package com.hoshino.springboot.annotation.aspect;

import com.hoshino.springboot.annotation.anno.Log;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.util.Arrays;

/**
 * @author huangyuehao
 * @date 2023-04-06
 */
@Aspect
@Order(1)
@Component
@Slf4j
public class LogAspect {

    @Pointcut("execution(public * com.hoshino.springboot.annotation.*.*(..))")
    public void pointCut() {}

    @Around("@annotation(log)")
    public Object around(ProceedingJoinPoint joinPoint, Log log) throws Throwable {
        if (log != null) {
            System.out.println("log.title() = " + log.title());
            System.out.println("log.isSaveRequestData() = " + log.isSaveRequestData());
            System.out.println("log.isSaveResponseData() = " + log.isSaveResponseData());
            System.out.println("log.includeParamNames() = " + Arrays.toString(log.includeParamNames()));
            System.out.println("log.excludeParamNames() = " + Arrays.toString(log.excludeParamNames()));
        }

        Object obj = null;
        try {
            obj = joinPoint.proceed();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return obj;
    }

    @After("pointCut()")
    public void after(JoinPoint joinPoint){
        Signature signature = joinPoint.getSignature();
        MethodSignature methodSignature = (MethodSignature) signature;
        Method method = methodSignature.getMethod();
        Log log = method.getAnnotation(Log.class);

        if (log != null) {
            System.out.println("log.title() = " + log.title());
            System.out.println("log.isSaveRequestData() = " + log.isSaveRequestData());
            System.out.println("log.isSaveResponseData() = " + log.isSaveResponseData());
            System.out.println("log.includeParamNames() = " + Arrays.toString(log.includeParamNames()));
            System.out.println("log.excludeParamNames() = " + Arrays.toString(log.excludeParamNames()));
        }
    }

}
