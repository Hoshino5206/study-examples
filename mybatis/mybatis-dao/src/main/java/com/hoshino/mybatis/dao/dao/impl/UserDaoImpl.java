package com.hoshino.mybatis.dao.dao.impl;

import com.hoshino.mybatis.dao.dao.UserDao;
import com.hoshino.mybatis.dao.entity.User;
import com.hoshino.mybatis.dao.utils.MybatisUtils;
import org.apache.ibatis.session.SqlSession;

import java.util.List;

/**
 * @author Yy_hoshino
 * @date 2021-04-20 2:09
 */
public class UserDaoImpl implements UserDao {

    public List<User> findAll() {
        SqlSession sqlSession = MybatisUtils.getSession();
        List<User> userList = sqlSession.selectList("userMapper.findAll");
        sqlSession.close();
        return userList;
    }

    public User findById(int id) {
        SqlSession sqlSession = MybatisUtils.getSession();
        User user = sqlSession.selectOne("userMapper.findById", id);
        sqlSession.close();
        return user;
    }

    public User add() {
        SqlSession sqlSession = MybatisUtils.getSession();
        User user = new User();
        user.setId(4);
        user.setName("王五");
        user.setPassword("wang123");
        user.setPhone("13577889900");
        sqlSession.insert("userMapper.add", user);
        // 提交事务
        sqlSession.commit();
        sqlSession.close();
        return user;
    }

    public User updateById(int id) {
        SqlSession sqlSession = MybatisUtils.getSession();
        User user = new User();
        user.setId(id);
        user.setName("老六");
        user.setPassword("liu6666");
        user.setPhone("13711445522");
        sqlSession.update("userMapper.updateById", user);
        sqlSession.close();
        return user;
    }

    public int deleteById(int id) {
        SqlSession sqlSession = MybatisUtils.getSession();
        int delete = sqlSession.delete("userMapper.deleteById", id);
        // 提交事务
        sqlSession.commit();
        sqlSession.close();
        return delete;
    }

}
