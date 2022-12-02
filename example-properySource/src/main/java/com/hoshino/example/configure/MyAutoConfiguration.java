package com.hoshino.example.configure;

import com.hoshino.example.properties.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * @author huangyuehao
 * @date 2022-11-29
 */
@Configuration
@EnableConfigurationProperties(Person.class)
public class MyAutoConfiguration {

    @Autowired
    private Person person;

    public MyAutoConfiguration() {
        System.out.println("person.toString() = " + person.toString());
    }

}
