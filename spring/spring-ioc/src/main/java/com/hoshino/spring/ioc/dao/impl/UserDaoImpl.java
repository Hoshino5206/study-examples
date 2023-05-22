package com.hoshino.spring.ioc.dao.impl;

import com.hoshino.spring.ioc.dao.UserDao;
import com.hoshino.spring.ioc.model.User;

import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

/**
 * @author Yy_hoshino
 * @date 2021-04-08 23:21
 */
public class UserDaoImpl implements UserDao {

    private List<String> list;
    private Set<String> set;
    private List<User> userList;
    private Map<String,User> userMap;
    private Properties properties;

    public UserDaoImpl() {
        super();
    }

    public UserDaoImpl(List<String> list, Set<String> set, List<User> userList, Map<String, User> userMap, Properties properties) {
        this.list = list;
        this.set = set;
        this.userList = userList;
        this.userMap = userMap;
        this.properties = properties;
    }

    public List<String> getList() {
        return list;
    }

    public void setList(List<String> list) {
        this.list = list;
    }

    public Set<String> getSet() {
        return set;
    }

    public void setSet(Set<String> set) {
        this.set = set;
    }

    public List<User> getUserList() {
        return userList;
    }

    public void setUserList(List<User> userList) {
        this.userList = userList;
    }

    public Map<String, User> getUserMap() {
        return userMap;
    }

    public void setUserMap(Map<String, User> userMap) {
        this.userMap = userMap;
    }

    public Properties getProperties() {
        return properties;
    }

    public void setProperties(Properties properties) {
        this.properties = properties;
    }

    @Override
    public void save() {
        System.out.println("save running...");
        System.out.println(list);
        System.out.println(set);
        System.out.println(userList);
        System.out.println(userMap);
        System.out.println(properties);
    }

}
