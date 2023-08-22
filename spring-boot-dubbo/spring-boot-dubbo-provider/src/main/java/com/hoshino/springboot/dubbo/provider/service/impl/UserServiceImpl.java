package com.hoshino.springboot.dubbo.provider.service.impl;

import com.hoshino.springboot.dubbo.interfaces.api.UserService;
import com.hoshino.springboot.dubbo.interfaces.entity.User;
import org.apache.dubbo.common.utils.StringUtils;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

/**
 * @author huangyuehao
 * @date 2023-07-06
 */
@DubboService
@Service
public class UserServiceImpl implements UserService {

    private static final List<User> USERS_CACHE = new ArrayList<>();

    @PostConstruct
    public void init() {
        USERS_CACHE.add(new User(1, "Tom", "Banana666"));
        USERS_CACHE.add(new User(2, "Jack", "Apple123"));
        USERS_CACHE.add(new User(3, "Cat", "Money9999"));
    }

    @Override
    public User getUserById(Integer id) {
        for (User user : USERS_CACHE) {
            boolean isMatch = StringUtils.isEquals(String.valueOf(user.getId()), String.valueOf(id));
            if (isMatch) {
                return user;
            }
        }
        return null;
    }

    @Override
    public List<User> getUserList() {
        return USERS_CACHE;
    }

}
