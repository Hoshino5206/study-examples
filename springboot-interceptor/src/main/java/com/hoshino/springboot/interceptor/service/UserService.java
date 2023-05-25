package com.hoshino.springboot.interceptor.service;

import com.hoshino.springboot.interceptor.entity.User;

import java.util.List;

/**
 * @author huangyuehao
 * @date 2023-05-25
 */
public interface UserService {

    List<User> getUserList();

}