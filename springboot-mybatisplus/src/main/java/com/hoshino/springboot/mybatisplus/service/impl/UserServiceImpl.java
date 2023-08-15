package com.hoshino.springboot.mybatisplus.service.impl;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.toolkit.IdWorker;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hoshino.springboot.mybatisplus.entity.User;
import com.hoshino.springboot.mybatisplus.mapper.UserMapper;
import com.hoshino.springboot.mybatisplus.service.UserService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * @author huangyuehao
 * @date 2023-06-28
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    @Resource
    private UserMapper userMapper;

    @Override
    @DS("master")
    public List<User> queryWrapper(User user) {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String startTime = simpleDateFormat.format(user.getCreateTime());
        String endTime = simpleDateFormat.format(new Date());

        // 雪花算法
        long snowFlakeId = IdWorker.getId(user);
        queryWrapper
//                .eq(user.getId() != null, "id", snowFlakeId)
                .eq(user.getIsDelete() != null, "is_delete", user.getIsDelete())
                .like(StringUtils.isNotBlank(user.getUsername()), "username", user.getUsername())
                .between(StringUtils.isNoneBlank(startTime, endTime), "create_time", startTime, endTime)
                .orderByDesc(StringUtils.isNoneBlank("update_time"), "update_time")
                .last("limit 3");

        return userMapper.selectList(queryWrapper);
    }

    @Override
    public Boolean updateWrapper(User user) {
        UpdateWrapper<User> updateWrapper = new UpdateWrapper<>();

        updateWrapper.eq(user.getId() != null, "id", user.getId())
                .eq(user.getIsDelete() != null, "is_delete", user.getIsDelete())
                .set(StringUtils.isNotBlank(user.getUsername()), "username", user.getUsername())
                .set(StringUtils.isNotBlank(user.getPassword()), "password", user.getPassword());

        int result = userMapper.update(user, updateWrapper);
        return result == 1;
    }

    @Override
    @DS("vm")
    public List<User> lambdaQueryWrapper(User user) {
        LambdaQueryWrapper<User> lambdaQueryWrapper = new LambdaQueryWrapper<>();

        lambdaQueryWrapper
//                .eq(user.getId() != null, User::getId, user.getId())
                .eq(user.getIsDelete() != null, User::getIsDelete, user.getIsDelete())
                .like(StringUtils.isNotBlank(user.getUsername()), User::getUsername, user.getUsername())
                .last("limit 3");

        return userMapper.selectList(lambdaQueryWrapper);
    }

    @Override
    public Boolean lambdaUpdateWrapper(User user) {
        LambdaUpdateWrapper<User> lambdaUpdateWrapper = new LambdaUpdateWrapper<>();

        lambdaUpdateWrapper.eq(user.getId() != null, User::getId, user.getId())
                .eq(user.getIsDelete() != null, User::getIsDelete, user.getIsDelete())
                .set(StringUtils.isNotBlank(user.getUsername()), User::getUsername, user.getUsername())
                .set(StringUtils.isNotBlank(user.getPassword()), User::getPassword, user.getPassword());

        int result = userMapper.update(user, lambdaUpdateWrapper);
        return result == 1;
    }
}
