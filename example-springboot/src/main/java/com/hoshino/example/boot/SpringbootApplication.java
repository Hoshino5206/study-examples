package com.hoshino.example.boot;

import com.hoshino.example.boot.configuration.LogbackSensitive;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class SpringbootApplication {

    public static void main (String[] args) {
        ConfigurableApplicationContext run = SpringApplication.run(SpringBootApplication.class, args);
        String[] beanDefinitionNames = run.getBeanDefinitionNames();
        for (String beanDefinitionName : beanDefinitionNames) {
            System.out.println("beanDefinitionName = " + beanDefinitionName);
        }
        LogbackSensitive sensitive = (LogbackSensitive) run.getBean("Test0");
        sensitive.test0();
    }

}
