package com.hoshino.springboot.mongodb;

import com.hoshino.springboot.mongodb.entity.Address;
import com.hoshino.springboot.mongodb.entity.User;
import com.hoshino.springboot.mongodb.service.UserService;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.Random;

/**
 * @author huangyuehao
 * @date 2023-06-28
 */
@SpringBootTest(classes = MongoDbApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@RunWith(SpringRunner.class)
public class MongodbTest {

    private static final String ID = "64902cfc1f66500f17406462";

    @Resource
    private UserService userService;

    @Test
    public void saveTest() {
        User user = new User();
        user.setId(ID);
        user.setUsername(RandomStringUtils.randomAlphabetic(8));
        user.setAge(new Random().nextInt(100));
        user.setAddress(new Address("cn", "shenzhen", "localhost"));

        userService.save(user);
    }

    @Test
    public void removeTest() {
        userService.remove(ID);
    }

    @Test
    public void updateTest() {
        User user = new User();
        user.setId(ID);
        user.setUsername(RandomStringUtils.randomAlphabetic(8));
        user.setAge(new Random().nextInt(100));
        user.setAddress(new Address("merican", "New York", "localhost"));

        userService.update(user);
    }

    @Test
    public void findById() {
        userService.findById(ID);
    }

}
