package com.hoshino.springboot.mybatisplus.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * @author huangyuehao
 * @date 2023-06-28
 */
@Data
public class User implements Serializable {

    private static final long serialVersionUID = -6675417131553360839L;

    private Integer id;

    private String username;

    private String password;

}
