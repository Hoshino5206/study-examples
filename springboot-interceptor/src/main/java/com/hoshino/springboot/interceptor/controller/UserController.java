package com.hoshino.springboot.interceptor.controller;

import com.hoshino.springboot.interceptor.common.ResponseResult;
import com.hoshino.springboot.interceptor.entity.User;
import com.hoshino.springboot.interceptor.service.UserServiceImpl;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author huangyuehao
 * @date 2023-01-06
 */
@RestController
@RequestMapping("/interceptor/user")
public class UserController {

    @Resource
    private UserServiceImpl userServiceImpl;

    @PostMapping("/list")
    public ResponseResult<List<User>> userList() {
        List<User> result = userServiceImpl.getUserList();
        return ResponseResult.success(result);
    }

}
