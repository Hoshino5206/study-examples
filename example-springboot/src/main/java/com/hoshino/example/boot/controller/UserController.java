package com.hoshino.example.boot.controller;

import com.hoshino.example.boot.configuration.LogbackSensitive;
import com.hoshino.example.boot.entity.Users;
import com.hoshino.example.boot.properties.ExampleProperties;
import com.hoshino.example.boot.properties.PersonProperties;
import com.hoshino.example.boot.service.UserService;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author huangyuehao
 * @date 2022-08-30
 */
@RestController
public class UserController {

    @Resource
    private PersonProperties personProperties;

    @Resource
    private ExampleProperties exampleProperties;

    @Resource
    private LogbackSensitive logbackSensitive;

    @Resource
    private UserService userService;

    @RequestMapping("/haha/{password}")
    public List<Users> findTest(@PathVariable("password") String password) throws Exception {
        String name = "zhangsan";
        List<Users> users = userService.findByName(name, password);
        System.out.println("666");
        return users;
    }

    @RequestMapping("/test0")
    public void test0() {
        System.out.println(personProperties.getField());
        System.out.println(personProperties.getMap());
        System.out.println(personProperties.getMapStu());
        System.out.println(personProperties.getList());
    }

    @RequestMapping("/test1")
    public void test1() {
        System.out.println(exampleProperties.getEnable());
        System.out.println(exampleProperties.getLocation());
    }

    @RequestMapping("/test2")
    public void test2() {
        logbackSensitive.console();
    }


}
