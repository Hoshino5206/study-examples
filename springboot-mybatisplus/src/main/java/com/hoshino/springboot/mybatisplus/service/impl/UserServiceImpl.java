package com.hoshino.springboot.mybatisplus.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hoshino.springboot.mybatisplus.entity.User;
import com.hoshino.springboot.mybatisplus.mapper.UserMapper;
import com.hoshino.springboot.mybatisplus.service.UserService;
import org.springframework.stereotype.Service;

/**
 * @author huangyuehao
 * @date 2023-06-28
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

}
