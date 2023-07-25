package com.hoshino.springboot.config.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author huangyuehao
 * @date 2023-07-25
 */
@Target(ElementType.PARAMETER)
@Retention(RetentionPolicy.RUNTIME)
public @interface MyRequestBody {

    /**
     * 是否必须出现的参数。
     */
    boolean required() default false;
    /**
     * 解析时用到的JSON的key。
     */
    String value() default "";
    /**
     * 集合元素的ClassType。只有在接口参数为List<E>的时候，需要把E的class传入。
     * 缺省值Class.class表示没有设置。
     */
    Class<?> elementType() default Class.class;
}
