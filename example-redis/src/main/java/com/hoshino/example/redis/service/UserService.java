package com.hoshino.example.redis.service;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;

/**
 * @author huangyuehao
 * @date 2022-12-16
 */
@Service
public class UserService {

    /**
     * redisTemplate.opsForValue(); 操作string
     * redisTemplate.opsForList();  操作list
     * redisTemplate.opsForSet();   操作set
     * redisTemplate.opsForZSet();  操作zSet
     * redisTemplate.opsForHash();  操作hash
     */
    @Resource
    private RedisTemplate<Object, Object> redisTemplate;

    /**
     * 删除key.
     * @param key
     * @param keys
     */
    public void delete(String key, String ...keys) {
        // 删除单个key
        redisTemplate.delete(key);
        // 删除多个keyv
        redisTemplate.delete(keys);
    }

    /**
     * 指定key的失效时间.
     * @param key
     * @param time
     */
    public void expire(String key,long time){
        redisTemplate.expire(key, time, TimeUnit.SECONDS);
    }

    /**
     * 根据key获取过期时间.
     * @param key
     * @return
     */
    public void getExpire(String key){
        Long expire = redisTemplate.getExpire(key);
        System.out.println("expire = " + expire);
    }

    /**
     * 判断key是否存在.
     * @param key
     * @return
     */
    public void hasKey(String key){
        Boolean isExist = redisTemplate.hasKey(key);
        System.out.println("isExist = " + isExist);
    }

    public void string() {
        //1、通过ValueOperations设置值和过期时间
        redisTemplate.opsForValue().set("StringKey1", "StringValue", 60, TimeUnit.SECONDS);

        //2、通过BoundValueOperations设置值和过期时间
        redisTemplate.boundValueOps("StringKey2").set(100,1, TimeUnit.MINUTES);

        //3、通过key获取value
        String str = (String)redisTemplate.opsForValue().get("StringKey2");

        //4、通过key自增/自减value,value必须是整形
        redisTemplate.opsForValue().increment("StringKey2", 10);
        redisTemplate.opsForValue().decrement("StringKey2", 10);
    }

    public void list() {
        //1、通过ValueOperations设置值
        redisTemplate.opsForList().set("ListKey2", 0, "ListValue2");
        redisTemplate.opsForList().leftPush("ListKey1", "ListValue1");

        //2、通过BoundValueOperations设置值
    }

    public void hash(String key, String hashKey, String value) {
        //1、通过ValueOperations设置值和过期时间
        redisTemplate.opsForHash().put(key, hashKey, value);
        redisTemplate.expire(key, 60, TimeUnit.SECONDS);

        //2、通过BoundValueOperations设置值和过期时间
        redisTemplate.boundHashOps(key).put(hashKey, value);
        redisTemplate.expire(key, 60, TimeUnit.SECONDS);

        //3、通过ValueOperations批量设置map
        redisTemplate.boundHashOps(key).putAll(new HashMap<>(8));
    }



}
