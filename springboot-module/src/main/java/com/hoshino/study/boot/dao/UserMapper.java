package com.hoshino.study.boot.dao;

import org.apache.ibatis.annotations.Mapper;

/**
 * @author huangyuehao
 * @date 2022-08-30
 */
@Mapper
public interface UserMapper {

    default int queryByName(String name) {
        return 10;
    }
}
