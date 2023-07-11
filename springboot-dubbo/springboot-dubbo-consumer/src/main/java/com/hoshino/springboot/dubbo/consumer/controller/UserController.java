package com.hoshino.springboot.dubbo.consumer.controller;

import com.hoshino.springboot.dubbo.interfaces.api.UserService;
import com.hoshino.springboot.dubbo.interfaces.entity.User;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author huangyuehao
 * @date 2023-07-06
 */
@RestController
@RequestMapping("/dubbo/user")
public class UserController {

    @DubboReference
    private UserService userService;

    @RequestMapping("/info")
    public User getUserInfo(Integer id) {
        return userService.getUserById(id);
    }

    @RequestMapping("/list")
    public List<User> getUserList() {
        return userService.getUserList();
    }
}
