package com.hoshino.study.boot.controller;

import com.hoshino.study.boot.entity.Users;
import com.hoshino.study.boot.properties.PersonProperties;
import com.hoshino.study.boot.service.UserService;
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
    private UserService userService;

    @RequestMapping("/haha/{password}")
    public List<Users> findTest(@PathVariable("password") String password) throws Exception {
        String name = "zhangsan";
        List<Users> users = userService.findByName(name, password);
        System.out.println("666");
        return users;
    }

    @RequestMapping("/pro")
    public void test0() {
        System.out.println(personProperties.getField());
        System.out.println(personProperties.getMap());
        System.out.println(personProperties.getMapStu());
        System.out.println(personProperties.getList());
    }

}
