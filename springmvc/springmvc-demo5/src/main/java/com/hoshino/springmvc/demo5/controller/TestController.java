package com.hoshino.springmvc.demo5.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author Yy_hoshino
 * @date 2022-04-15 14:08
 */
@Controller
public class TestController {

    @RequestMapping("/**/testInterceptor")
    public String testInterceptor() {
        return "success";
    }

    @RequestMapping("/testExceptionHandler")
    public String testExceptionHandler() {
        System.out.println(1 / 0);
        return "success";
    }

}
