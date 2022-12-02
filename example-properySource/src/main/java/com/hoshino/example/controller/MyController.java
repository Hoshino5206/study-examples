package com.hoshino.example.controller;

import com.hoshino.example.properties.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author huangyuehao
 * @date 2022-11-29
 */
@RestController
public class MyController {

    @Autowired
    private Person person;

    @RequestMapping("/test")
    public String test() {
        System.out.println(person.toString());
        return person.toString();
    }
}
