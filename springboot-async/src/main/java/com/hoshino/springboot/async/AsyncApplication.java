package com.hoshino.springboot.async;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

/**
 * @author huangyuehao
 * @date 2023-05-15
 */
@SpringBootApplication
public class AsyncApplication {

    public static void main (String[] args) {
        SpringApplication.run(AsyncApplication.class, args);
    }

}
