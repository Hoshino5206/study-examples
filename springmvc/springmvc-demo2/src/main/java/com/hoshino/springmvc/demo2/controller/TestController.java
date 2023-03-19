package com.hoshino.springmvc.demo2.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author Yy_hoshino
 * @date 2022-04-13 12:58
 */
@Controller
public class TestController {

    @RequestMapping("/")
    public String index() {
        return "index";
    }

    @RequestMapping("/param")
    public String param() {
        return "test_param";
    }

}
