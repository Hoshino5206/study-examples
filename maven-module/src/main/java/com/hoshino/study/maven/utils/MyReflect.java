package com.hoshino.study.maven.utils;

import com.hoshino.study.maven.domain.Users;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Arrays;

/**
 * @author huangyuehao
 * @date 2022-09-07
 */
public class MyReflect {

    public static void main (String[] args) {

        Users users = new Users(010, "三毛", "10086", 18);
        Class<? extends Users> clazz = users.getClass();
        Class<?>[] declaredClasses = clazz.getDeclaredClasses();
        Method[] declaredMethods = clazz.getDeclaredMethods();
        Field[] declaredFields = clazz.getDeclaredFields();

        System.out.println(Arrays.toString(declaredClasses));
        System.out.println(Arrays.toString(declaredMethods));
        System.out.println(Arrays.toString(declaredFields));
    }
}
