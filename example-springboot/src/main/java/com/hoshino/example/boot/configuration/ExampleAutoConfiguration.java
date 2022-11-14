package com.hoshino.example.boot.configuration;

import com.hoshino.example.boot.properties.ExampleProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author huangyuehao
 * @date 2022-11-08
 */
@Configuration(proxyBeanMethods = false)
@EnableConfigurationProperties(ExampleProperties.class)
public class ExampleAutoConfiguration {

    private final ExampleProperties exampleProperties;

    public ExampleAutoConfiguration (ExampleProperties exampleProperties) {
        this.exampleProperties = exampleProperties;
    }

    @Bean
    public LogbackSensitive logbackSensitive() {
        LogbackSensitive logbackSensitive = new LogbackSensitive();
        logbackSensitive.setLocation(exampleProperties.getLocation());
        logbackSensitive.setEnable(exampleProperties.getEnable());
        return logbackSensitive;
    }

}
