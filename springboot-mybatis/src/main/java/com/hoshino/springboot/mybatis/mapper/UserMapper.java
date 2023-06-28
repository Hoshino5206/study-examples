package com.hoshino.springboot.mybatis.mapper;

import com.hoshino.springboot.mybatis.entity.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * (User)表数据库访问层
 *
 * @author huangyuehao
 * @date 2023-06-28
 */
@Mapper
public interface UserMapper {
    
    /**
     * 通过主键删除
     *
     * @param id 主键
     * @return 影响行数
     */
    Integer deleteByPrimaryKey(Integer id);
    
    /**
     * 根据条件新增数据
     *
     * @param user 实例对象
     * @return 影响行数
     */
    Integer insertSelective(User user);

    /**
     * 通过主键修改数据
     *
     * @param user 实例对象
     * @return 影响行数
     */
    Integer updateByPrimaryKey(User user);
    
    /**
     * 通过主键选择性修改数据
     *
     * @param user 实例对象
     * @return 影响行数
     */
    Integer updateByPrimaryKeySelective(User user);
    
    /**
     * 查询全部
     *
     * @return 对象列表
     */
    List<User> selectAll();
    
    /**
     * 通过主键查询
     *
     * @param id 主键
     * @return 实例对象
     */
    User selectByPrimaryKey(Integer id);

}
