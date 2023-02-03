package com.hoshino.dubbo.annotation.config;

import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

/**
 * @author huangyuehao
 * @date 2023-02-03
 */
@Configuration
@EnableDubbo
@PropertySource("classpath:/dubbo-consumer.properties")
public class ConsumerConfiguration {

}
