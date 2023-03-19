package com.hoshino.spring.jdbc;

/**
 * @author Yy_hoshino
 * @date 2021-04-12 18:08
 */
public class Account {

    private Integer id;         // 账户id

    private String name;    // 用户名

    private double money;     // 账户余额

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getMoney() {
        return money;
    }

    public void setMoney(double money) {
        this.money = money;
    }

    @Override
    public String toString() {
        return "Account{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", money=" + money +
                '}';
    }
}
