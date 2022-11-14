package com.hoshino.example.boot.configuration;

import com.hoshino.example.boot.properties.ExampleProperties;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.Resource;

/**
 * @author huangyuehao
 * @date 2022-11-08
 */
@Data
@Configuration
@Slf4j
public class LogbackSensitive {

    private Boolean enable = false;

    private String location = StringUtils.EMPTY;

    @Resource
    private ExampleProperties exampleProperties;

    public void console() {
        System.out.println("enable = " + enable);
        System.out.println("location = " + location);
    }


    @Bean("Test0")
    public void test0() {
        log.info("bean: {}", exampleProperties.getLocation());
    }


}
