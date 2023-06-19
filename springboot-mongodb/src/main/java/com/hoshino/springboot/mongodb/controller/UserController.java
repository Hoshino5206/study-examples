package com.hoshino.springboot.mongodb.controller;

import com.hoshino.springboot.mongodb.entity.User;
import com.hoshino.springboot.mongodb.service.UserService;
import com.mongodb.client.result.DeleteResult;
import com.mongodb.client.result.UpdateResult;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Random;

/**
 * @author huangyuehao
 * @date 2023-06-19
 */
@RestController
@RequestMapping("/mongodb/admin")
public class UserController {

    private static final String ID = "64902cfc1f66500f17406462";

    @Resource
    private UserService userService;

    @RequestMapping("/save")
    public User save() {
        User user = new User();
        user.setUsername(RandomStringUtils.randomAlphabetic(8));
        user.setAge(new Random().nextInt(100));

        return userService.save(user);
    }

    @RequestMapping("/remove")
    public DeleteResult remove() {
        return userService.remove(ID);
    }

    @RequestMapping("/update")
    public UpdateResult update() {
        User user = new User();
        user.setId(ID);
        user.setUsername(RandomStringUtils.randomAlphabetic(8));
        user.setAge(new Random().nextInt(100));

        return userService.update(user);
    }

    @RequestMapping("/findById")
    public User findById() {
        return userService.findById(ID);
    }

}
