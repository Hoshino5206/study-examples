package com.hoshino.springboot.redis.service;

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
    public void expire(String key, long time){
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
        redisTemplate.boundValueOps("StringKey2").set(100L,1, TimeUnit.MINUTES);

        //3、通过key获取value
        Long str2 = (Long) redisTemplate.opsForValue().get("StringKey2");
        System.out.println("StringKey2 = " + str2);
        // boundValueOps实际调用的方法 -> 仍然是ValueOperations接口的get方法
        // Object stringKey2 = redisTemplate.boundValueOps("StringKey2").get();
        // System.out.println("stringKey2 = " + stringKey2);

        // 4、通过key自增/自减value，value必须是整形
        redisTemplate.opsForValue().increment("StringKey2", 10);
        redisTemplate.opsForValue().decrement("StringKey2", 10);
    }

    public void list() {
        //1、通过ValueOperations设置值
        redisTemplate.opsForList().set("ListKey1", 0, "ListValue2");
        redisTemplate.opsForList().leftPush("ListKey1", "ListValue1");
        redisTemplate.opsForList().rightPush("ListKey1", "ListValue3");

        //2、通过BoundValueOperations设置值
        redisTemplate.boundListOps("ListKey2").set(0, "ListValue2");
        redisTemplate.boundListOps("ListKey2").leftPush("ListValue1");
        redisTemplate.boundListOps("ListKey2").rightPush("ListValue3");
    }

    public void set() {

    }

    public void zSet() {

    }

    public void hash() {
        //1、通过ValueOperations设置值和过期时间
        redisTemplate.opsForHash().put("Key1", "HashKey", "HashValue");
        redisTemplate.expire("Key1", 60, TimeUnit.SECONDS);

        //2、通过BoundValueOperations设置值和过期时间
        redisTemplate.boundHashOps("Key1").put("HashKey", "HashValue");
        redisTemplate.expire("Key1", 60, TimeUnit.SECONDS);

        //3、通过ValueOperations批量设置map
        redisTemplate.boundHashOps("Key1").putAll(new HashMap<>(8));
    }

}
