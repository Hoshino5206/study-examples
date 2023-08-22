package com.hoshino.springboot.multisource;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

/**
 * @author huangyuehao
 * @date 2023-04-23
 */
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
public class MultiSourceApplication {

    public static void main(String[] args) {
        SpringApplication.run(MultiSourceApplication.class, args);
    }

}
