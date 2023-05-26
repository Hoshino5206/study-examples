package com.hoshino.springboot.annotation.aspect;

import com.hoshino.springboot.annotation.annotation.Logging;
import com.hoshino.springboot.annotation.util.LogUtil;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * @author huangyuehao
 * @date 2023-04-06
 */
@Aspect
@Order(1)
@Component
@Slf4j
public class LogAspect {

    /**
     * 切面获取注解方式1
     *
     * @param joinPoint
     * @param logging
     * @return
     * @throws Throwable
     */
    @Around("@annotation(logging)")
    public Object around(ProceedingJoinPoint joinPoint, Logging logging) throws Throwable {
        LogUtil.logPrintOut(logging);

        Object obj = null;
        try {
            obj = joinPoint.proceed();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return obj;
    }

//    @Pointcut("execution(public * com.hoshino.springboot.annotation.*.*(..))")
//    public void pointCut() {}
//
//    /**
//     * 切面获取注解方式2
//     *
//     * @param joinPoint
//     */
//    @After("pointCut()")
//    public void after(JoinPoint joinPoint){
//        Signature signature = joinPoint.getSignature();
//        MethodSignature methodSignature = (MethodSignature) signature;
//        Method method = methodSignature.getMethod();
//        Logging logging = method.getAnnotation(Logging.class);
//        LogUtil.logPrintOut(logging);
//    }

}
