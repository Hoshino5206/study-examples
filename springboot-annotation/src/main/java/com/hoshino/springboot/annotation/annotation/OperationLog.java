package com.hoshino.springboot.annotation.annotation;

import java.lang.annotation.*;

/**
 * @author huangyuehao
 * @date 2023-04-06
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ ElementType.PARAMETER, ElementType.METHOD })
@Documented
public @interface OperationLog {

    /**
     * 模块
     */
    String title() default "";

    /**
     * 是否保存请求的参数
     */
    boolean isSaveRequestData() default true;

    /**
     * 是否保存响应的参数
     */
    boolean isSaveResponseData() default true;

    /**
     * 包括指定的请求参数
     */
    String[] includeParamNames() default {};

    /**
     * 排除指定的请求参数
     */
    String[] excludeParamNames() default {};

}
