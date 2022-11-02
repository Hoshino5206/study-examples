package com.hoshino.example.spring.test.configuration;

import com.hoshino.example.spring.test.condition.OnClassConditional;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author huangyuehao
 * @date 2022-11-02
 */
@Configuration
@ConditionalOnClass(OnClassConditional.class)
public class ConditionalOnClassConfiguration {

    @Bean
    public String create() {
        return "ConditionalOnClassConfiguration create a bean";
    }

}
