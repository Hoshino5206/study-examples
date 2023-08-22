package com.hoshino.springboot.validator.service;

import com.hoshino.springboot.validator.entity.User;
import org.springframework.stereotype.Service;

/**
 * @author Akino
 * @date 2023-08-18
 */
@Service
public interface UserService {

    void checkUser(User user);
}
