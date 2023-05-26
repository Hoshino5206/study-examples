package com.hoshino.springboot.autoconfigure.person;

import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

/**
 * @author huangyuehao
 * @date 2022-11-29
 */
@Configuration
@EnableConfigurationProperties(PersonProperties.class)
@ConditionalOnClass(PersonConfiguration.class)
@ConditionalOnProperty(prefix = PersonProperties.PREFIX, name = "enable", havingValue = "true")
@PropertySource(value = "classpath:com/hoshino/springboot/autoconfigure/person.properties", encoding = "UTF-8")
public class PersonAutoConfiguration {

    private final PersonProperties personProperties;

    public PersonAutoConfiguration (PersonProperties personProperties) {
        this.personProperties = personProperties;
    }

    @Bean
    @ConditionalOnMissingBean
    @ConditionalOnClass
    public PersonConfiguration personConfiguration() {
        return new PersonConfiguration(personProperties);
    }

}

