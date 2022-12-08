package com.hoshino.example.propertySource.controller;

import com.hoshino.example.propertySource.configure.PersonProperties;
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
    private PersonProperties personProperties;

    @RequestMapping("/test")
    public String test() {
        System.out.println(personProperties.toString());
        return personProperties.toString();
    }
}
