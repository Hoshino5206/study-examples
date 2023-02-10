package com.hoshino.spring;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.env.Environment;

import java.util.Arrays;

/**
 * @author huangyuehao
 * @date 2023-02-06
 */
public class SpringApplication {

    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("classpath:spring-config.xml");
        Environment environment = context.getEnvironment();
        System.out.println("environment = " + environment);

        String[] beanDefinitionNames = context.getBeanDefinitionNames();
        System.out.println("beanDefinitionNames = " + Arrays.toString(beanDefinitionNames));

        String applicationName = context.getApplicationName();
        System.out.println("applicationName = " + applicationName);

    }

}
