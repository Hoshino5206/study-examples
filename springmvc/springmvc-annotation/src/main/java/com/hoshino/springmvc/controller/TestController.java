package com.hoshino.springmvc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author Yy_hoshino
 * @date 2022-04-15 4:31
 */
@Controller
public class TestController {

    @RequestMapping("/")
    public String index(){
        return "index";
    }

}
