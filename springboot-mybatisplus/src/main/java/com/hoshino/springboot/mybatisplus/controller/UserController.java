package com.hoshino.springboot.mybatisplus.controller;

import com.hoshino.springboot.mybatisplus.entity.User;
import com.hoshino.springboot.mybatisplus.service.UserService;
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
@RequestMapping("/mybatisplus/user")
public class UserController {

    @Resource
    private UserService userService;

    @RequestMapping("/delete")
    public String delete(Integer id) {
        return userService.removeById(id) ? String.valueOf(id) : "error";
    }

    @RequestMapping("/save")
    public String save() {
        User user = new User();
        user.setUsername(RandomStringUtils.randomAlphabetic(8));
        user.setPassword(RandomStringUtils.randomAlphabetic(8));
        user.setIsDelete(0);
        return userService.save(user) ? String.valueOf(user) : "error";
    }

    @RequestMapping("/update")
    public String update(Integer id) {
        User user = new User();
        user.setId(id);
        user.setUsername(RandomStringUtils.randomAlphabetic(8));
        user.setPassword(RandomStringUtils.randomAlphabetic(8));
        return userService.updateById(user) ? String.valueOf(user) : "error";
    }

    @RequestMapping("/list")
    public List<User> getUserList() {
        return userService.list();
    }

    @RequestMapping("/getUserById")
    public User getUserById(Integer id) {
        return userService.getById(id);
    }

}
