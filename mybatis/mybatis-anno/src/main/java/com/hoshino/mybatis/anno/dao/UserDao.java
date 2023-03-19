package com.hoshino.mybatis.anno.dao;

import com.hoshino.mybatis.anno.entity.User;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * @author Yy_hoshino
 * @date 2021-04-20 19:13
 */
public interface UserDao {

    @Select("select * from user")
    List<User> findAll();

    @Select("select * from user where id = #{id}")
    User findById(int id);

    @Insert("insert into user(id, name, password, phone) values (#{id}, #{name}, #{password}, #{phone})")
    @Options(useGeneratedKeys = true, keyColumn = "id")
    int add(User user);

    @Update("update user set name = #{name}, password = #{password}, phone = #{phone} where id = #{id}")
    int updateById(User user);

    @Delete("delete from user where id = #{id}")
    int deleteById(int id);

}
