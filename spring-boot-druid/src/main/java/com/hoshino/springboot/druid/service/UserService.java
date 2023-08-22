package com.hoshino.springboot.druid.service;

import com.hoshino.springboot.druid.entity.User;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author huangyuehao
 * @date 2023-06-28
 */
@Service
public interface UserService {

    Boolean remove(Integer id);

    Boolean saveNew(User user);

    Boolean update(User user);

    List<User> getUserList();

    User getUserById(Integer id);

}
