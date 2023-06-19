package com.hoshino.springboot.mongodb.service;

import com.hoshino.springboot.mongodb.entity.User;
import com.mongodb.client.result.DeleteResult;
import com.mongodb.client.result.UpdateResult;

/**
 * @author huangyuehao
 * @date 2023-05-23
 */
public interface UserService {

    User save(User user);

    DeleteResult remove(String id);

    UpdateResult update(User user);

    User findById(String id);

}
