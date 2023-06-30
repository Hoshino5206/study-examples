package com.hoshino.springboot.pagehelper.service.impl;

import com.hoshino.springboot.pagehelper.entity.User;
import com.hoshino.springboot.pagehelper.mapper.UserMapper;
import com.hoshino.springboot.pagehelper.service.UserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author huangyuehao
 * @date 2023-06-28
 */
@Service
public class UserServiceImpl implements UserService {

    @Resource
    private UserMapper userMapper;

    @Override
    public List<User> getUserList() {
        return userMapper.selectAll();
    }
}
