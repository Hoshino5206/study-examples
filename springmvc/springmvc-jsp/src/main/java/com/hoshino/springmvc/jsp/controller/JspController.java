package com.hoshino.springmvc.jsp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author Yy_hoshino
 * @date 2022-04-15 14:08
 */
@Controller
public class JspController {

    @RequestMapping("/success")
    public String success() {
        return "success";
    }

}
