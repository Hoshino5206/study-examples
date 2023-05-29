package com.hoshino.springboot.redis;

import com.hoshino.springboot.redis.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

/**
 * @author huangyuehao
 * @date 2022-12-19
 */
@SpringBootTest(classes = RedisApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@RunWith(SpringRunner.class)
public class UserServiceTest {

    @Resource
    private UserService userService;

    @Test
    public void testRedisString() {
        userService.string();
    }

    @Test
    public void testRedisList() {
        userService.list();
    }

    @Test
    public void testRedisSet() {
        userService.set();
    }

    @Test
    public void testRedisZSet() {
        userService.zSet();
    }

    @Test
    public void testRedisHash() {
        userService.hash();
    }

}
