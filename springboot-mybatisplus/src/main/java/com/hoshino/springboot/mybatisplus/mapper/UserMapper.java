package com.hoshino.springboot.mybatisplus.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.hoshino.springboot.mybatisplus.entity.User;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author huangyuehao
 * @date 2023-06-28
 */
@Mapper
public interface UserMapper extends BaseMapper<User> {

}
