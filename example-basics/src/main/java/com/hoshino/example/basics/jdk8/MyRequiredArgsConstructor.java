package com.hoshino.example.basics.jdk8;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;

/**
 * @author huangyuehao
 * @date 2022-08-02
 */
@RequiredArgsConstructor
public class MyRequiredArgsConstructor {

    private String name;

    // 被final修饰
    private final String age;

    @NonNull
    private String sex;
}
