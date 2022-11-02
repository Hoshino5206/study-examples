package com.hoshino.example.boot.service.impl;

import com.hoshino.example.boot.dao.UserMapper;
import com.hoshino.example.boot.entity.Users;
import com.hoshino.example.boot.service.UserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author huangyuehao
 * @date 2022-08-30
 */
@Service
public class UserServiceImpl implements UserService {

    @Resource
    private UserMapper userMapper;

    @Override
    public List<Users> findByName(String name, String password) throws Exception {
//        int i = userMapper.queryByName(name);
//        if (i == 10) {
//            throw new ShenyuException("错误");
//        }
        Users users1 = new Users();
        users1.setUsername(name);
        users1.setAge(20);
        users1.setPassword(password);

        Users users2 = new Users();
        users2.setUsername(name);
        users2.setAge(3);
        users2.setPassword("dev" + name + "log");

        return Stream.of(users1, users2).collect(Collectors.toList());
    }
}
