package com.hoshino.mybatis.dao.dao;

import com.hoshino.mybatis.dao.entity.User;

import java.util.List;

/**
 * @author Yy_hoshino
 * @date 2021-04-20 1:38
 */
public interface UserDao {

    List<User> findAll();

    User findById(int id);

    User add();

    User updateById(int id);

    int deleteById(int id);

}
