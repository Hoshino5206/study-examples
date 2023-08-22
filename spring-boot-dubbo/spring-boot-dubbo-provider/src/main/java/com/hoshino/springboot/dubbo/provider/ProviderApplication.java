package com.hoshino.springboot.dubbo.provider;

import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author huangyuehao
 * @date 2023-07-06
 */
@SpringBootApplication
@EnableDubbo
@Slf4j
public class ProviderApplication {

    public static void main(String[] args) {
        SpringApplication.run(ProviderApplication.class, args);
        log.info("dubbo provider service started......");
    }

}
