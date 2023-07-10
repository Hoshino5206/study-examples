package com.hoshino.springboot.dubbo.interfaces.entity;

import java.io.Serializable;

/**
 * @author huangyuehao
 * @date 2023-07-06
 */
public class User implements Serializable {

    private static final long serialVersionUID = -2499313738786556177L;

    private Integer id;

    private String username;

    private String password;

    public User(Integer id, String username, String password) {
        this.id = id;
        this.username = username;
        this.password = password;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
