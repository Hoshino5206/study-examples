package com.hoshino.example.maven.domain;

import lombok.*;

/**
 * @author huangyuehao
 * @date 2022-07-22
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Users extends UserFather {

    private Integer id;
    private String username;
    private String password;
    private Integer age;

    public Integer salary;

    public Users(Integer id, String username, String password, Integer age) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.age = age;
    }

    private void haha(String username) {
        System.out.println("reflect invoke method haha(), username = " + username);
    }
}
