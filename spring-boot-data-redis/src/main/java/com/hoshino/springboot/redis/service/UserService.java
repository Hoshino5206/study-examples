package com.hoshino.springboot.redis.service;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ZSetOperations;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.*;
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
    private RedisTemplate<String, Object> redisTemplate;

    /**
     * 删除key.
     * @param key
     * @param keys
     */
    public void delete(String key, String ...keys) {
        // 删除单个key
        redisTemplate.delete(key);
        // 删除多个keys
        redisTemplate.delete(Arrays.asList(keys));
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

    /**
     * String类型相关操作.
     */
    public void string() {
        //1、通过ValueOperations设置值和过期时间
        redisTemplate.opsForValue().set("StringKey1", "StringValue", 60, TimeUnit.SECONDS);

        //2、通过BoundValueOperations设置值和过期时间
        redisTemplate.boundValueOps("StringKey2").set(100L,60, TimeUnit.SECONDS);

        //3、通过key获取value
        Long str2 = (Long) redisTemplate.opsForValue().get("StringKey2");
        System.out.println("StringKey2 = " + str2);
        // boundValueOps实际调用的方法 -> 仍然是ValueOperations接口的get方法
        Long stringKey2 = (Long) redisTemplate.boundValueOps("StringKey2").get();
        System.out.println("stringKey2 = " + stringKey2);

        // 4、通过key自增/自减value，value必须是整形
        redisTemplate.opsForValue().increment("StringKey2", 10L);
        redisTemplate.opsForValue().decrement("StringKey2", 10L);
    }

    /**
     * List类型相关操作.
     */
    public void list() {
        //1、通过ListOperations设置值
        redisTemplate.opsForList().set("ListKey1", 0, "ListValue2");
        redisTemplate.opsForList().leftPush("ListKey1", "ListValue1");
        redisTemplate.opsForList().rightPush("ListKey1", "ListValue3");

        //2、通过BoundListOperations设置值
        redisTemplate.boundListOps("ListKey2").set(0, "ListValue2");
        redisTemplate.boundListOps("ListKey2").leftPush("ListValue1");
        redisTemplate.boundListOps("ListKey2").rightPush("ListValue3");

        //3、通过索引获取列表中的元素
        Object listValue = redisTemplate.opsForList().index("ListKey1", 1);
        System.out.println("通过索引获取列表中的元素, listValue = " + listValue);

        //4、获取列表指定范围内的元素
        List<Object> valueList = redisTemplate.opsForList().range("ListKey1", 0, 2);
        System.out.println("获取列表指定范围内的元素, valueList = " + valueList);

        //5、从左侧弹出一个元素
        Object leftPop = redisTemplate.opsForList().leftPop("ListKey1");
        System.out.println("leftPop = " + leftPop);

        //6、从右侧弹出一个元素
        Object rightPop = redisTemplate.opsForList().rightPop("ListKey1");
        System.out.println("rightPop = " + rightPop);

        //5、删除某元素
        Long remove = redisTemplate.opsForList().remove("ListKey1", 0, "ListValue3");
        System.out.println("remove = " + remove);
    }

    /**
     * Set类型相关操作.
     */
    public void set() {
        //1、通过SetOperations设置值
        redisTemplate.opsForSet().add("SetKey1", "SetValue1", "SetValue2", "SetValue3", "SetValue4");

        //2、通过BoundSetOperations设置值
        redisTemplate.boundSetOps("SetKey2").add("SetValue1", "SetValue2", "SetValue3", "SetValue4");

        //3、获取集合所有元素
        Set<Object> members = redisTemplate.opsForSet().members("SetKey1");
        System.out.println("members = " + members);

        //4、查询value是否存在一个set中
        Boolean isExist = redisTemplate.opsForSet().isMember("SetKey1", "SetValue4");
        System.out.println("isExist = " + isExist);

        //5、移除并返回集合的一个随机元素
        Object popValue = redisTemplate.opsForSet().pop("SetKey1");
        System.out.println("popValue = " + popValue);

        //6、移除元素
        Long remove = redisTemplate.opsForSet().remove("SetKey1", "SetValue1", "SetValue2");
        System.out.println("remove = " + remove);

        //7、获取两个集合的差集
        Set<Object> difference = redisTemplate.opsForSet().difference("SetKey1", "SetKey2");
        System.out.println("difference = " + difference);

        //8、key集合与otherKey集合的差集存储到destKey中
        Long differenceAndStore = redisTemplate.opsForSet().differenceAndStore("SetKey1", "SetKey2", "SetKey3");
        System.out.println("differenceAndStore = " + differenceAndStore);

        //9、获取两个集合的交集
        Set<Object> intersection = redisTemplate.opsForSet().intersect("SetKey1", "SetKey2");
        System.out.println("intersection = " + intersection);

        //10、key集合与otherKey集合的交集存储到destKey集合中
        Long intersectAndStore = redisTemplate.opsForSet().intersectAndStore("SetKey1", "SetKey2", "SetKey3");
        System.out.println("intersectAndStore = " + intersectAndStore);
    }

    /**
     * ZSet类型相关操作.
     */
    public void zSet() {
        //1、通过ZSetOperations设置值
        redisTemplate.opsForZSet().add("ZSetKey1", "ZSetValue1", 5);
        redisTemplate.opsForZSet().add("ZSetKey1", "ZSetValue2", 18);
        redisTemplate.opsForZSet().add("ZSetKey1", "ZSetValue3", 8);
        redisTemplate.opsForZSet().add("ZSetKey1", "ZSetValue4", 13);

        //2、通过BoundZSetOperations设置值
        redisTemplate.boundZSetOps("ZSetKey2").add("ZSetValue1", 10);
        redisTemplate.boundZSetOps("ZSetKey2").add("ZSetValue2", 20);

        //3、移除元素
        redisTemplate.opsForZSet().remove("ZSetKey1", "ZSetValue1", "ZSetValue2");

        //4、获取集合的元素, 从小到大排序
        redisTemplate.opsForZSet().range("ZSetKey1", 0, 2);

        //5、获取集合中key、value元素对应的score值；
        Double score = redisTemplate.opsForZSet().score("ZSetKey1", "ZSetValue2");
        System.out.println("score = " + score);

        //6、返回元素在集合的排名,有序集合是按照元素的score值由小到大排列
        Long rank = redisTemplate.opsForZSet().rank("ZSetKey1", "ZSetValue1");
        System.out.println("rank = " + rank);

        //7、返回元素在集合的排名,按元素的score值由大到小排列
        Long reverseRank = redisTemplate.opsForZSet().reverseRank("ZSetKey1", "ZSetValue2");
        System.out.println("reverseRank = " + reverseRank);

        //8、获取集合中给定区间的元素(start 开始位置，end 结束位置, -1查询所有)；
        Set<ZSetOperations.TypedTuple<Object>> keys = redisTemplate.opsForZSet().reverseRangeWithScores("ZSetKey1", 0, 3);
        System.out.println("keys = " + keys);

    }

    /**
     * Hash类型相关操作.
     */
    public void hash() {
        //1、通过HashOperations设置值和过期时间
        redisTemplate.opsForHash().put("Key1", "HashKey1", "HashValue1");
        redisTemplate.opsForHash().put("Key1", "HashKey2", "HashValue2");
        redisTemplate.opsForHash().put("Key1", "HashKey3", "HashValue3");
        redisTemplate.expire("Key1", 60, TimeUnit.SECONDS);

        //2、通过BoundHashOperations设置值和过期时间
        redisTemplate.boundHashOps("Key2").put("HashKey", "HashValue");
        redisTemplate.expire("Key2", 60, TimeUnit.SECONDS);

        //3、通过HashOperations批量设置map
        redisTemplate.opsForHash().putAll("Key1", new HashMap<>(8));

        //4、根据key获取一个字段（fields）的set集合
        Set<Object> fields = redisTemplate.opsForHash().keys("Key1");
        System.out.println("fields = " + fields);

        //5、根据key获取所有value（field，value）
        Map<Object, Object> entries = redisTemplate.opsForHash().entries("Key1");
        System.out.println("entries = " + entries);

        //5、根据key和field获取value值
        Object hashValue = redisTemplate.opsForHash().get("Key1", "HashKey1");
        System.out.println("hashValue = " + hashValue);

        //6、删除一个或多个哈希表字段
        Long delete = redisTemplate.opsForHash().delete("key1", "HashKey1", "HashKey2");
        System.out.println("delete = " + delete);
    }

}
