package com.hoshino.springboot.mongodb.service.impl;

import com.hoshino.springboot.mongodb.entity.User;
import com.hoshino.springboot.mongodb.service.UserService;
import com.mongodb.client.result.UpdateResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author huangyuehao
 * @date 2023-05-25
 */
@Service
@Slf4j
public class UserServiceImpl implements UserService {

    @Resource
    private MongoTemplate mongoTemplate;

    @Override
    public User save(User user) {
//        mongoTemplate.insert(user);         // 如果主键存在，则会更新数据
        return mongoTemplate.save(user);    // 如果主键存在，则会抛异常
    }

    @Override
    public User remove(String id) {
        Query query = new Query(Criteria.where("_id").is(id));

//        mongoTemplate.remove(query, User.class);
        return mongoTemplate.findAndRemove(query, User.class);
    }

    @Override
    public UpdateResult update(User user) {
        Query query = new Query(Criteria.where("_id").is(user.getId()));

        Update update = new Update();
        update.set("username", user.getUsername());
        update.set("age", user.getAge());
        update.set("address", user.getAddress());

        return mongoTemplate.updateFirst(query, update, User.class);
    }

    @Override
    public User findById(String id) {
        Query query = new Query(Criteria.where("_id").is(id));

//        mongoTemplate.findById(id, User.class);
        return mongoTemplate.findOne(query, User.class);
    }
}
