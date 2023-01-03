package com.hoshino.example.redis;

import com.hoshino.example.redis.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

/**
 * @author huangyuehao
 * @date 2022-12-19
 */
@SpringBootTest(classes = RedisApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@RunWith(SpringRunner.class)
@AutoConfigureMockMvc
@Slf4j
public class UserServiceTest {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
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
