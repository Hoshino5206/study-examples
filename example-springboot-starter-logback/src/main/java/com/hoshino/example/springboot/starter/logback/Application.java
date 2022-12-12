package com.hoshino.example.springboot.starter.logback;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author huangyuehao
 * @date 2022-12-12
 */
@SpringBootApplication
@Slf4j
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
        log.info("password:{}", "dqui&2bnijS%89BS61.sq8&s");
    }
}
