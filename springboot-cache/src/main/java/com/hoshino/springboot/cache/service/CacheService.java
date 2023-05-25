package com.hoshino.springboot.cache.service;

import com.hoshino.springboot.cache.entity.User;

import java.util.List;

/**
 * @author huangyuehao
 * @date 2023-01-16
 */
public interface CacheService {

    User getUser(Integer id);

    List<User> getUserList();

    void create(User user);

    void updateById(User user);

    void deleteById(Integer id);

}