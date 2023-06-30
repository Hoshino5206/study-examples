package com.hoshino.springboot.druid.controller;

import com.hoshino.springboot.druid.entity.User;
import com.hoshino.springboot.druid.service.UserService;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * @author huangyuehao
 * @date 2023-06-28
 */
@RestController
@RequestMapping("/druid/user")
public class UserController {

    @Resource
    private UserService userService;

    @RequestMapping("/delete")
    public String delete(Integer id) {
        return userService.remove(id) ? String.valueOf(id) : "error";
    }

    @RequestMapping("/save")
    public String save() {
        User user = new User();
        user.setUsername(RandomStringUtils.randomAlphabetic(8));
        user.setPassword(RandomStringUtils.randomAlphabetic(8));
        user.setCreator("Tom");
        user.setUpdater("Tom");
        user.setCreateTime(new Date());
        user.setUpdateTime(new Date());
        user.setIsDelete(0);
        return userService.saveNew(user) ? String.valueOf(user) : "error";
    }

    @RequestMapping("/update")
    public String update(Integer id) {
        User user = new User();
        user.setId(id);
        user.setUsername(RandomStringUtils.randomAlphabetic(8));
        user.setPassword(RandomStringUtils.randomAlphabetic(8));
        user.setUpdater("Jack");
        user.setUpdateTime(new Date());
        return userService.update(user) ? String.valueOf(user) : "error";
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
