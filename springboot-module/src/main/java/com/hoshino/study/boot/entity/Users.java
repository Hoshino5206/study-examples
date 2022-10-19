package com.hoshino.study.boot.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author huangyuehao
 * @date 2022-09-13
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Users {
    private String username;
    private String password;
    private Integer age;
}
