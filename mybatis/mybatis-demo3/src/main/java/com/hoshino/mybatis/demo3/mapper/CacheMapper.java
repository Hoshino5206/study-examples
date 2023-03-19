package com.hoshino.mybatis.demo3.mapper;

import com.hoshino.mybatis.demo3.entity.Emp;
import org.apache.ibatis.annotations.Param;

public interface CacheMapper {

    Emp getEmpByEid(@Param("eid") Integer eid);

    void insertEmp(Emp emp);

}
