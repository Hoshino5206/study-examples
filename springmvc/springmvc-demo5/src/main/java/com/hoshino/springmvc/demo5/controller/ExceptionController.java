package com.hoshino.springmvc.demo5.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * @author Yy_hoshino
 * @date 2022-04-15 14:08
 */
@Controller
public class ExceptionController {

    @ExceptionHandler(value = {ArithmeticException.class, NullPointerException.class})
    public String testException(Exception ex, Model model) {
        model.addAttribute("ex", ex);
        return "error";
    }

}
