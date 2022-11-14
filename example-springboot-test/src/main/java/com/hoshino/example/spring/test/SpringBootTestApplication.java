package com.hoshino.example.spring.test;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

/**
 * @author huangyuehao
 * @date 2022-11-09
 */
@SpringBootApplication
public class SpringBootTestApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext run = SpringApplication.run(SpringBootTestApplication.class, args);
        String[] beans = run.getBeanDefinitionNames();
        for (String bean : beans) {
            System.out.println("bean = " + bean);
        }
    }

}
