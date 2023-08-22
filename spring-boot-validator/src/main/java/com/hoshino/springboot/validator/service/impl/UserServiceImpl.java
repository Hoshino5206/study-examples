package com.hoshino.springboot.validator.service.impl;

import com.hoshino.springboot.validator.entity.User;
import com.hoshino.springboot.validator.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @author Akino
 * @date 2023-08-18
 */
@Service
@Slf4j
public class UserServiceImpl implements UserService {

    @Override
    public void checkUser(User user) {
      log.info("update user successfully ......");
    }
}
