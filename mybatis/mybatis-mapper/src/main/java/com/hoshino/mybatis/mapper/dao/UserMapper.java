package com.hoshino.mybatis.mapper.dao;

import com.hoshino.mybatis.mapper.entity.User;

import java.util.List;

/**
 * @author Yy_hoshino
 * @date 2021-04-20 1:38
 */
public interface UserMapper {

    List<User> findAll();

    User findById(int id);

    int add(User user);

    int updateById(User user);

    int deleteById(int id);

}
