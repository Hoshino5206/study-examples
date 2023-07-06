package com.hoshino.springboot.consumer;

import com.hoshino.dubbo.springboot.DemoService;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.DubboReference;
import org.apache.dubbo.config.annotation.DubboService;
import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

/**
 * @author huangyuehao
 * @date 2023-02-03
 */
@SpringBootApplication
@EnableDubbo
@DubboService
@Slf4j
public class ConsumerApplication {

    @DubboReference
    private DemoService demoService;

    public static void main(String[] args) {
        ConfigurableApplicationContext applicationContext = SpringApplication.run(ConsumerApplication.class, args);
        log.info("dubbo consumer service started......");

        ConsumerApplication bean = applicationContext.getBean(ConsumerApplication.class);
        String result = bean.demoService.sayHello("Tomcat");
        log.info("hello, {}", result);
    }

}
