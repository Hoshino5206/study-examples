package com.hoshino.springboot.mybatisplus;

import com.hoshino.springboot.mybatisplus.entity.User;
import com.hoshino.springboot.mybatisplus.service.UserService;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

/**
 * @author huangyuehao
 * @date 2023-06-28
 */
@SpringBootTest(classes = MybatisPlusApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@RunWith(SpringRunner.class)
public class UserServiceTest {


    @Resource
    private UserService userService;

    @Test
    public void removeTest() {
        userService.removeById(2);
    }

    @Test
    public void saveNewTest() {
        User user = new User();
        user.setUsername(RandomStringUtils.randomAlphabetic(8));
        user.setPassword(RandomStringUtils.randomAlphabetic(8));
        userService.save(user);
    }

    @Test
    public void updateTest() {
        User user = new User();
        user.setId(1);
        user.setUsername(RandomStringUtils.randomAlphabetic(8));
        user.setPassword(RandomStringUtils.randomAlphabetic(8));
        userService.updateById(user);
    }

    @Test
    public void getUserListTest() {
        userService.list();
    }

    @Test
    public void getUserByIdTest() {
        userService.getById(1);
    }

}
