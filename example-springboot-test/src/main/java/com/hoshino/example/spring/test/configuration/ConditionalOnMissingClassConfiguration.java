package com.hoshino.example.spring.test.configuration;

import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingClass;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author huangyuehao
 * @date 2022-11-02
 */
@Configuration
@ConditionalOnMissingClass("OnMissingClassConditional")
public class ConditionalOnMissingClassConfiguration {

    @Bean
    public String miss() {
        return "ConditionalOnMissingClassConfiguration miss a bean";
    }

}
