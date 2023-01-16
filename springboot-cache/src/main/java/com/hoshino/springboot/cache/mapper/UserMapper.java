package com.hoshino.springboot.cache.mapper;

import com.hoshino.springboot.cache.model.User;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author huangyuehao
 * @date 2023-01-16
 */
@Repository
public interface UserMapper {

    User selectById(int id);

    List<User> selectList(Object o);

    void insert(User user);

    void updateById(User user);

    void deleteById(int id);
}
