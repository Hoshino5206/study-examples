package com.hoshino.spring.ioc.anno.util;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.PropertySource;

/**
 * @author Yy_hoshino
 * @date 2021-04-09 23:47
 */

// 用于指定当前类是一个 Spring 配置类，当创建容器时会从该类上加载注解
@Configuration
// 用于指定 Spring 在初始化容器时要扫描的包
//@ComponentScan("com.hoshino.spring.ioc.anno")
@ComponentScan({"com.hoshino.spring.ioc.anno.dao.impl", "com.hoshino.spring.ioc.anno.service.impl", "com.hoshino.spring.ioc.anno.util"})
// 加载properties配置文件，相当于<context:property-placeholder location="classpath:jdbc.properties"></context:property-placeholder>
@PropertySource("classpath:jdbc.properties")
// 用于导入其他配置类，此处导入的是一个DataSourceConfiguration类，当导入多个类时用","隔开
@Import({DataSourceConfiguration.class})
public class SpringConfiguration {
}
