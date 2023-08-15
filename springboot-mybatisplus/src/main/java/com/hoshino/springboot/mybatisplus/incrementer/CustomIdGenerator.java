package com.hoshino.springboot.mybatisplus.incrementer;

import com.baomidou.mybatisplus.core.incrementer.IdentifierGenerator;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.reflection.MetaObject;
import org.apache.ibatis.reflection.SystemMetaObject;

import java.util.concurrent.atomic.AtomicLong;

/**
 * 自定义ID生成器
 * @author Akino
 * @date 2023-08-16
 */
@Slf4j
public class CustomIdGenerator implements IdentifierGenerator {

    private final AtomicLong al = new AtomicLong(1);

    @Override
    public Long nextId(Object entity) {
        //可以将当前传入的class全类名来作为bizKey,或者提取参数来生成bizKey进行分布式Id调用生成.
        String bizKey = entity.getClass().getName();
        log.info("bizKey:{}", bizKey);

        MetaObject metaObject = SystemMetaObject.forObject(entity);
        String username = (String) metaObject.getValue("username");

        final long id = al.getAndAdd(1);
        log.info("为{}生成主键值->:{}", username, id);

        return id;
    }
}