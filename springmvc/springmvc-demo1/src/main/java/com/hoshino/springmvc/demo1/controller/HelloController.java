package com.hoshino.springmvc.demo1.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author Yy_hoshino
 * @date 2022-04-13 12:21
 */
@Controller
public class HelloController {

    @RequestMapping("/")
    public String index() {
        // 设置视图名称
        return "index";
    }

    @RequestMapping("target")
    public String toTarget() {
        return "target";
    }

}
