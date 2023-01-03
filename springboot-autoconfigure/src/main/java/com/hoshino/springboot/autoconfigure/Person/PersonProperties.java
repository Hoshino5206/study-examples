package com.hoshino.springboot.autoconfigure.Person;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @author huangyuehao
 * @date 2022-11-29
 */
@Data
@ConfigurationProperties(PersonProperties.PREFIX)
public class PersonProperties {

    public final static String PREFIX = "com.hoshino.example";

    private boolean enable;

    private String id;

    private String name;

    private int age;

    private Date birthday;

    private Address address;

    private List<String> list;

    private Map<String, String> map;

    private List<Address> listAdd;

    private Map<String, Address> addressMap;

    @Data
    public static class Address {
        private String province;

        private String county;

        private String distinct;
    }

}

