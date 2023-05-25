package com.hoshino.springboot.cache;

import com.hoshino.springboot.cache.entity.User;
import com.hoshino.springboot.cache.service.CacheService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

/**
 * @author huangyuehao
 * @date 2023-01-18
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class CacheServiceTest {
    @Resource
    private CacheService cacheService;

    @Test
    public void getById() {
        cacheService.getUser(2);
    }

    @Test
    public void getAllUsers() {
        cacheService.getUserList();
    }

    @Test
    public void createUser() {
        User user = new User();
        user.setId(2);
        user.setUsername("Tom");
        user.setPassword("667788");
        cacheService.create(user);
    }

    @Test
    public void updateUser() {
        User user = new User();
        user.setId(2);
        user.setUsername("test");
        user.setPassword("123456");
        cacheService.updateById(user);
    }

    @Test
    public void deleteById() {
        cacheService.deleteById(2);
    }

}
