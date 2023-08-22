package com.hoshino.springboot.dubbo.interfaces.api;

import com.hoshino.springboot.dubbo.interfaces.entity.User;

import java.util.List;

/**
 * @author huangyuehao
 * @date 2023-07-06
 */
public interface UserService {

    User getUserById(Integer id);

    List<User> getUserList();

}
