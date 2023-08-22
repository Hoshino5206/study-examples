package com.hoshino.springboot.validator.controller;

import com.hoshino.springboot.validator.common.ResponseResult;
import com.hoshino.springboot.validator.entity.User;
import com.hoshino.springboot.validator.service.UserService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author Akino
 * @date 2023-08-18
 */
@RestController
@RequestMapping("/validator/user")
public class UserController {

    @Resource
    private UserService userService;

    @PostMapping("/check")
    public ResponseResult<User> check(@RequestBody @Validated User user) {
        userService.checkUser(user);
        return ResponseResult.success(user);
    }
}
