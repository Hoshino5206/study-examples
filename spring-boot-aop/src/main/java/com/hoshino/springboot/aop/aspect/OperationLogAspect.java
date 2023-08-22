package com.hoshino.springboot.aop.aspect;

import com.hoshino.springboot.aop.annotation.OperationLog;
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
@Order(1)
@Component
@Slf4j
public class OperationLogAspect {

    /**
     * execution：一般用于指定方法的执行，用的最多。
     * within：指定某些类型的全部方法执行，也可用来指定一个包。
     * this：Spring Aop是基于代理的，生成的bean也是一个代理对象，this就是这个代理对象，当这个对象可以转换为指定的类型时，对应的切入点就是它了，Spring Aop将生效。
     * target：当被代理的对象可以转换为指定的类型时，对应的切入点就是它了，Spring Aop将生效。
     * args：当执行的方法的参数是指定类型时生效。
     * @target：当代理的目标对象上拥有指定的注解时生效。
     * @args：当执行的方法参数类型上拥有指定的注解时生效。
     * @within：与@target类似，看官方文档和网上的说法都是@within只需要目标对象的类或者父类上有指定的注解，则@within会生效，而@target则是必须是目标对象的类上有指定的注解。而根据笔者的测试这两者都是只要目标类或父类上有指定的注解即可。
     * @annotation：当执行的方法上拥有指定的注解时生效。
     * bean：当调用的方法是指定的bean的方法时生效。
     */
    @Pointcut("@annotation(com.hoshino.springboot.aop.annotation.OperationLog)")
    public void annotationCut() {}

    @Pointcut("execution(public * com.hoshino.springboot.aop.controller.*.*(..))")
    public void executionCut() {}


    // 前置通知，在目标方法之前执行
    @Before("annotationCut()")
    public void deBefore(JoinPoint joinPoint){
        // 接收到请求，记录请求内容
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();

        // 记录下请求内容
        log.info("前置通知。");
        log.info("ARGS: {}", Arrays.toString(joinPoint.getArgs()));
        log.info("CLASS_METHOD: {}", joinPoint.getSignature().getDeclaringTypeName() + "." + joinPoint.getSignature().getName());

        log.info("Ip: {}", request.getRemoteAddr());
        log.info("Url: {}", request.getRequestURL());
        log.info("Uri: {}", request.getRequestURI());
        log.info("HttpMethod: {}", request.getMethod());
        log.info("ServletPath: {}", request.getServletPath());

    }

    /**
     * 环绕通知，环绕增强，相当于MethodInterceptor
     *
     * @param joinPoint
     * @param operationLog
     * @return
     * @throws Throwable
     */
    @Around("@annotation(operationLog)")
    public Object around(ProceedingJoinPoint joinPoint, OperationLog operationLog) throws Throwable {
//        如果不在注解的value上绑定@annotation，则通过方法签名方式获取注解
//        Signature signature = joinPoint.getSignature();
//        MethodSignature methodSignature = (MethodSignature) signature;
//        Method method = methodSignature.getMethod();
//        OperationLog operationLog = method.getAnnotation(OperationLog.class);

        if (operationLog != null) {
            log.info("operationLog.description: {}", operationLog.description());
            log.info("operationLog.type: {}", operationLog.type());
        }

        Object obj = null;
        try {
            log.info("环绕通知。方法执行前......");
            obj = joinPoint.proceed();
            log.info("环绕通知。方法执行后......");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return obj;
    }

    /**
     * 后置通知
     *
     * @param ret
     * @throws Throwable
     */
    @AfterReturning(returning = "ret", pointcut = "executionCut()")
    public void doAfterReturning(Object ret) throws Throwable {
        // 处理完请求，返回内容
        log.info("后置通知。方法的返回值: {}" , ret);
    }

    /**
     * 后置异常通知
     *
     * @param joinPoint
     */
    @AfterThrowing("executionCut()")
    public void afterThrowing(JoinPoint joinPoint){
        log.info("后置异常通知。方法异常时执行.....");
    }

    /**
     * 后置最终通知，final增强，不管是抛出异常或者正常退出都会执行
     *
     * @param joinPoint
     */
    @After("executionCut()")
    public void after(JoinPoint joinPoint){
        log.info("后置最终通知。方法最后执行.....");
    }

}
