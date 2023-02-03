package com.hoshino.dubbo.springboot.provider;

import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author huangyuehao
 * @date 2023-02-03
 */
@SpringBootApplication
@EnableDubbo
public class ProviderApplication {

    public static void main(String[] args) {
        SpringApplication.run(ProviderApplication.class, args);
        System.out.println("dubbo service started");
    }

}
