package com.hoshino.springboot.multisource.annotation;

import java.lang.annotation.*;

/**
 * 多数据源注解
 *
 * @author huangyuehao
 * @date 2023-04-23
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD})
@Documented
public @interface DataSource {

    String value() default "";
}

