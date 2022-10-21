package com.hoshino.study.apollo.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author huangyuehao
 * @date 2022-10-20
 */
@RestController
public class TestController {

    @Value(value = "${mxn.name}")
    private String name;

    @RequestMapping("/test")
    public String test(){
        return "hello world "+name;
    }

}
