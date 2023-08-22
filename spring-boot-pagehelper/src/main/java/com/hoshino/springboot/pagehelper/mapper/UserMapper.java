package com.hoshino.springboot.pagehelper.mapper;

import com.hoshino.springboot.pagehelper.entity.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author huangyuehao
 * @date 2023-06-28
 */
@Mapper
public interface UserMapper {

    /**
     * 查询全部
     *
     * @return 对象列表
     */
    List<User> selectAll();
}
