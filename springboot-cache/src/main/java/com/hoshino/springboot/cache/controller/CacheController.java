package com.hoshino.springboot.cache.controller;

import com.hoshino.springboot.cache.entity.User;
import com.hoshino.springboot.cache.service.CacheService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author huangyuehao
 * @date 2023-05-25
 */
@RestController
@RequestMapping("/cache/user")
public class CacheController {

    @Resource
    private CacheService cacheService;

    @RequestMapping("/list")
    public List<User> userList() {
        return cacheService.getUserList();
    }

    @RequestMapping("/findById")
    public User list(Integer id) {
        return cacheService.getUser(id);
    }


}
