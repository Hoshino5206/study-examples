package com.hoshino.springboot.mybatis.controller;

import com.hoshino.springboot.mybatis.entity.User;
import com.hoshino.springboot.mybatis.service.UserService;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author huangyuehao
 * @date 2023-06-28
 */
@RestController
@RequestMapping("/mybatis/user")
public class UserController {

    @Resource
    private UserService userService;

    @RequestMapping("/remove")
    public String remove(Integer id) {
        return userService.remove(id) ? "success" : "error";
    }

    @RequestMapping("/save")
    public String save() {
        User user = new User();
        user.setUsername(RandomStringUtils.randomAlphabetic(8));
        user.setPassword(RandomStringUtils.randomAlphabetic(8));
        return userService.saveNew(user) ? "success" : "error";
    }

    @RequestMapping("/update")
    public String update(Integer id) {
        User user = new User();
        user.setId(id);
        user.setUsername(RandomStringUtils.randomAlphabetic(8));
        user.setPassword(RandomStringUtils.randomAlphabetic(8));
        return userService.update(user) ? "success" : "error";
    }

    @RequestMapping("/list")
    public List<User> getUserList() {
        return userService.getUserList();
    }

    @RequestMapping("/getUserById")
    public User getUserById(Integer id) {
        return userService.getUserById(id);
    }

}
