package com.hoshino.example.boot.service;

import com.hoshino.example.boot.entity.Users;

import java.util.List;

/**
 * @author huangyuehao
 * @date 2022-08-30
 */
public interface UserService {
    List<Users> findByName(String name, String password) throws Exception;
}
