package com.hoshino.redis;

import com.alibaba.fastjson.JSONObject;
import redis.clients.jedis.Transaction;

/**
 * @author huangyuehao
 * @date 2022-12-16
 */
public class Jedis {
    public static void main (String[] args) {
        //使用fastjson将实体类转换为json格式
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("name" , "Tc.l");
        jsonObject.put("age" , 99);
        //{"name":"Tc.l","age":99}
        String jsonString = jsonObject.toJSONString();

        redis.clients.jedis.Jedis jedis = new redis.clients.jedis.Jedis("127.0.0.1" , 6379);
        //清空数据库
        jedis.flushDB();
        //watch命令 监听key: {"name":"Tc.l","age":99}
        jedis.watch(jsonString);
        //开启事务
        Transaction multi = jedis.multi();
        try {
            multi.set("user1" , jsonString);
            multi.set("user2" , jsonString);
            // 执行事务
            multi.exec();
        } catch (Exception e) {
            e.printStackTrace();
            //如果异常，放弃事务
            multi.discard();
        } finally {
            System.out.println(jedis.get("user1"));
            System.out.println(jedis.get("user2"));
            jedis.close();
        }
    }
}
