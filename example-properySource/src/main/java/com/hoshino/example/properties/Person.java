package com.hoshino.example.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @author huangyuehao
 * @date 2022-11-29
 */
@Data
@Configuration
@ConfigurationProperties(prefix = "person")
@PropertySource(value = "classpath:person.properties",encoding = "UTF-8")
public class Person {

    private String id;

    private String name;

    private int age;

    private boolean isManager;

    private Date birthday;

    private Map<String, Object> map;

    private List<String> list;

    private Address address;

}

