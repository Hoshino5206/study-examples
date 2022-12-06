package com.hoshino.example.security.controlller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author huangyuehao
 * @date 2022-11-30
 */
@RestController
public class MyController {

    @RequestMapping("/test")
    public String test(){
        return "login success";
    }

}
