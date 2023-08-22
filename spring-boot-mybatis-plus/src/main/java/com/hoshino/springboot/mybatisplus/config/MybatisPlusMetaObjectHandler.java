package com.hoshino.springboot.mybatisplus.config;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * mybatisPlus 元数据处理
 *
 * @author huangyuehao
 * @date 2023-06-29
 */
@Component
@Slf4j
public class MybatisPlusMetaObjectHandler implements MetaObjectHandler {

    private static final ThreadLocal<String> THREAD_LOCAL = new ThreadLocal<>();

    /**
     * 自定义元数据对象处理器，插入操作自动填充.
     *
     * @param metaObject 元数据对象.
     */
    @Override
    public void insertFill(MetaObject metaObject) {
        log.info("公共字段自动填充[insert]......");

        setCurrentUser("Tom");
        metaObject.setValue("creator", getCurrentUser());
        metaObject.setValue("updater", getCurrentUser());
        metaObject.setValue("createTime", new Date());
        metaObject.setValue("updateTime", new Date());
        clear();
    }

    /**
     * 自定义元数据对象处理器，更新操作自动填充.
     *
     * @param metaObject 元数据对象.
     */
    @Override
    public void updateFill(MetaObject metaObject) {
        log.info("公共字段自动填充[update]......");

        setCurrentUser("Jack");
        metaObject.setValue("updater", getCurrentUser());
        metaObject.setValue("updateTime", new Date());
        clear();
    }

    public static void setCurrentUser(String username){
        THREAD_LOCAL.set(username);
    }

    public static String getCurrentUser(){
        return THREAD_LOCAL.get();
    }

    public static void clear(){
        THREAD_LOCAL.remove();
    }

}
