package com.hoshino.study.apollo;

import com.ctrip.framework.apollo.spring.annotation.EnableApolloConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author huangyuehao
 * @date 2022-10-20
 */
@SpringBootApplication
@EnableApolloConfig
public class ApolloApplication {
    public static void main(String[] args) {
        SpringApplication.run(ApolloApplication.class, args);
    }
}
