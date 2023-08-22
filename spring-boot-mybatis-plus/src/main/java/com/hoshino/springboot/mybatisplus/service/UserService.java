package com.hoshino.springboot.mybatisplus.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.IService;
import com.hoshino.springboot.mybatisplus.entity.User;

import java.util.List;

/**
 * @author huangyuehao
 * @date 2023-06-28
 */
public interface UserService extends IService<User> {

    List<User> queryWrapper(User user);

    Boolean updateWrapper(User user);

    List<User> lambdaQueryWrapper(User user);

    Boolean lambdaUpdateWrapper(User user);
}
