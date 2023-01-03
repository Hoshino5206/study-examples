package com.hoshino.example.springboot.aop.annotation;

import com.hoshino.example.springboot.aop.constants.OperationLogType;

import java.lang.annotation.*;

/**
 * 操作日志记录注解.
 * @author huangyuehao
 * @date 2022-12-14
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface OperationLog {

    /**
     * 描述。
     */
    String description() default "";

    /**
     * 操作类型。
     */
    int type() default OperationLogType.OTHER;

}
