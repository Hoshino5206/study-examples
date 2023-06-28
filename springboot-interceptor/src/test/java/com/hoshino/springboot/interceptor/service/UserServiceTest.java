package com.hoshino.springboot.interceptor.service;

import com.hoshino.springboot.interceptor.InterceptorApplication;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

/**
 * @author huangyuehao
 * @date 2023-05-25
 */
@SpringBootTest(classes = InterceptorApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@RunWith(SpringRunner.class)
public class UserServiceTest {

    @Resource
    private UserService userService;

    @Test
    public void getUserListTest() {
        userService.getUserList();
    }

}
