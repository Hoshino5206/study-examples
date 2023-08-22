package com.hoshino.springboot.jdbc.entity;

import lombok.Data;

/**
 * @author Akino
 * @date 2023-05-30
 */
@Data
public class Account {

    private Integer id;

    private String name;

    private Double money;

}