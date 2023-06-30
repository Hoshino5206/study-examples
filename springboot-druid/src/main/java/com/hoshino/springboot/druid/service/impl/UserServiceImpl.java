package com.hoshino.springboot.druid.service.impl;

import com.hoshino.springboot.druid.entity.User;
import com.hoshino.springboot.druid.mapper.UserMapper;
import com.hoshino.springboot.druid.service.UserService;
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
    public Boolean remove(Integer id) {
        return userMapper.deleteByPrimaryKey(id) > 0;
    }

    @Override
    public Boolean saveNew(User user) {
        return userMapper.insertSelective(user) > 0;
    }

    @Override
    public Boolean update(User user) {
        return userMapper.updateByPrimaryKeySelective(user) > 0;
    }

    @Override
    public List<User> getUserList() {
        return userMapper.selectAll();
    }

    @Override
    public User getUserById(Integer id) {
        return userMapper.selectByPrimaryKey(id);
    }

}
