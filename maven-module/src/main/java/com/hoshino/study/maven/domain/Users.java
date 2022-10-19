package com.hoshino.study.maven.domain;

import lombok.*;

/**
 * @author huangyuehao
 * @date 2022-07-22
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class Users {

    private Integer id;
    private String username;
    private String password;
    private Integer age;
}
