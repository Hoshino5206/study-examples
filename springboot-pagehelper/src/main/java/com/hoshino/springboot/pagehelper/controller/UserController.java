package com.hoshino.springboot.pagehelper.controller;

import com.github.pagehelper.PageInfo;
import com.github.pagehelper.page.PageMethod;
import com.hoshino.springboot.pagehelper.entity.User;
import com.hoshino.springboot.pagehelper.service.UserService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author huangyuehao
 * @date 2023-06-28
 */
@RestController
@RequestMapping("/pagehelper/user")
public class UserController {

    @Resource
    private UserService userService;

    @RequestMapping("/list")
    public PageInfo<User> getUserList() {
//        return PageMethod.startPage(1, 20).doSelectPageInfo(() -> userService.getUserList());

        PageMethod.startPage(2, 5);
        List<User> userList = userService.getUserList();
        return new PageInfo<>(userList);
    }

}
