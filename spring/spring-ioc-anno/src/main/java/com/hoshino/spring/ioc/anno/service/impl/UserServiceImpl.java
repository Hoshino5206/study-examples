package com.hoshino.spring.ioc.anno.service.impl;

import com.hoshino.spring.ioc.anno.dao.UserDao;
import com.hoshino.spring.ioc.anno.service.UserService;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.annotation.Resource;

/**
 * @author Yy_hoshino
 * @date 2021-04-10 0:47
 */
//@Component("userService")
//一般使用这种，方便标识Bean的id="userService"
@Service("userService")
//Bean的Scope="singleton"单例
@Scope("singleton")
public class UserServiceImpl implements UserService {

    /* 相当于@Resource
    @Autowired
    @Qualifier("userDao")
    */
    @Resource(name = "userDao")
    private UserDao userDao;

    public UserServiceImpl() {
    }

    //@Autowired
    public UserServiceImpl(UserDao userDao) {
        this.userDao = userDao;
    }

    //@Autowired
    //@Resource(name = "userDao")
    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    // 使用@PostConstruct标注初始化方法，相当于bean里面属性init-method
    @PostConstruct
    public void postInit() {
        System.out.println("Bean的初始化成功...");
    }

    // 使用@PreDestroy标注销毁方法，相当于bean里面属性destroy-method
    @PreDestroy
    public void preDestroy() {
        System.out.println("Bean的销毁成功...");
    }

    @Override
    public void save() {
        userDao.save();
        System.out.println("save runned...");
    }

}
