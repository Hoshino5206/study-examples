package com.hoshino.springboot.interceptor.service;

import com.hoshino.springboot.interceptor.entity.User;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

/**
 * @author huangyuehao
 * @date 2023-05-25
 */
@Service
public class UserServiceImpl implements UserService {

    @Override
    public List<User> getUserList() {
        return Collections.singletonList(new User(1, "John", 18));
    }

}
