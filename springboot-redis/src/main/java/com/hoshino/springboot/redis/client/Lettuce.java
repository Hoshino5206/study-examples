package com.hoshino.springboot.redis.client;

import io.lettuce.core.RedisClient;
import io.lettuce.core.RedisURI;
import io.lettuce.core.SetArgs;
import io.lettuce.core.api.StatefulRedisConnection;
import io.lettuce.core.api.sync.RedisCommands;

/**
 * @author huangyuehao
 * @date 2022-12-16
 */
public class Lettuce {
    public static void main (String[] args) {
        // <1>  创建Redis地址信息
        RedisURI uri = RedisURI.builder()
                .withHost("127.0.0.1")
                .withPort(6379)
                .withDatabase(0)
                .build();
        // <2> 创建客户端
        RedisClient redisClient = RedisClient.create(uri);
        // <3> 创建线程安全的连接
        StatefulRedisConnection<String, String> connection = redisClient.connect();
        // <4> 创建同步命令
        RedisCommands<String, String> sync = connection.sync();

        SetArgs setArgs = SetArgs.Builder.nx().ex(5);
        sync.set("name", "throwable", setArgs);
        String result = sync.get("name");
        System.out.println("result = " + result);

        connection.close();   // <5> 关闭连接
        redisClient.shutdown();  // <6> 关闭客户端
    }
}
