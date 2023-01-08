package com.hoshino.springboot.autoconfigure.Person;

import lombok.Data;

/**
 * @author huangyuehao
 * @date 2022-12-08
 */
@Data
public class PersonConfiguration {

    private PersonProperties properties;

    public PersonConfiguration(PersonProperties personProperties) {
        this.properties = personProperties;
    }

}
