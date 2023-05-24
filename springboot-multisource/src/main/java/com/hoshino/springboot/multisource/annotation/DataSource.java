package com.hoshino.springboot.multisource.annotation;

import java.lang.annotation.*;

/**
 * 主要用于标记Service所依赖的数据源类型。
 *
 * @author huangyuehao
 * @date 2023-04-23
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD})
@Documented
public @interface DataSource {

    /**
     * 标注的数据源类型
     * @return 当前标注的数据源类型。
     */
    String value() default "";
}

