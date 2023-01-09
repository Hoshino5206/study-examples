package com.hoshino.springmvc.interceptor.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author huangyuehao
 * @date 2023-01-06
 */
@RestController
public class MyController {

    @RequestMapping("/test")
    public String test() {
        return "ok";
    }

}
