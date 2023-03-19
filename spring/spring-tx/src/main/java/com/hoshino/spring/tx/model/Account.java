package com.hoshino.spring.tx.model;

/**
 * @author Yy_hoshino
 * @date 2021-04-14 0:00
 */
public class Account {

    private int id;

    private String name;

    private double money;

    public int getId() {
        return id;
    }

    public void setId(int id) {
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

}
