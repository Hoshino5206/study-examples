package com.hoshino.springboot.pagehelper.service;

import com.hoshino.springboot.pagehelper.entity.User;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author huangyuehao
 * @date 2023-06-28
 */
@Service
public interface UserService {

    List<User> getUserList();
}
