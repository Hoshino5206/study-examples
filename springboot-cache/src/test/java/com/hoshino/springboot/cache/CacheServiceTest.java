package com.hoshino.springboot.cache;

import com.hoshino.springboot.cache.model.User;
import com.hoshino.springboot.cache.service.CacheService;
import lombok.extern.slf4j.Slf4j;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

/**
 * @author huangyuehao
 * @date 2023-01-18
 */
@SpringBootTest(classes = CacheApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@RunWith(SpringRunner.class)
@AutoConfigureMockMvc
@Slf4j
public class CacheServiceTest {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private CacheService cacheService;

    public void getById() {
        cacheService.getById(5);
    }

    public void getAllUsers() {
        cacheService.getAllUsers();
    }

    public void createUser() {
        User user = new User();
        user.setId(1);
        user.setUsername("test");
        user.setPassword("test");
        cacheService.createUser(user);
    }

    public void updateUser() {
        User user = new User();
        user.setId(1);
        user.setUsername("test");
        user.setPassword("123456");
        cacheService.updateUser(user);
    }

    public void deleteById() {
        cacheService.deleteById(5);
    }

}
