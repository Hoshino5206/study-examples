package com.hoshino.springboot.mongodb.service.impl;

import com.hoshino.springboot.mongodb.service.UserService;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author huangyuehao
 * @date 2023-05-25
 */
@Service
public class UserServiceImpl implements UserService {

    @Resource
    private MongoTemplate mongoTemplate;

}
