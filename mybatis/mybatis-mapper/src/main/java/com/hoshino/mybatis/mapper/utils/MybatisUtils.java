package com.hoshino.mybatis.mapper.utils;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.Reader;

/**
 * @author Yy_hoshino
 * @date 2021-04-20 2:05
 */
public class MybatisUtils {

    private static SqlSessionFactory sqlSessionFactory = null;

    //初始化SqlSessionFactory对象
    static {
        try {
            //使用mybatis提供的Resources类加载Mybatis的配置文件
            Reader reader = Resources.getResourceAsReader("mybatis-config.xml");
            //构建SqlSessionFactory工厂
            sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //获取SqlSession对象的静态方法
    public static SqlSession getSession() {
        return sqlSessionFactory.openSession();
    }

}
