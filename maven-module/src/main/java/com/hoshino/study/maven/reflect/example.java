package com.hoshino.study.maven.reflect;

import com.hoshino.study.maven.utils.MyGson;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * @author huangyuehao
 * @date 2022-09-15
 */
public class example {
    public static void main (String[] args) throws ClassNotFoundException {
        MyGson myGson = new MyGson();
        System.out.println(myGson.toString());
        Class<?> clazz = Class.forName("com.hoshino.study.maven.utils.MyGson");
        Method[] declaredMethods = clazz.getDeclaredMethods();
        for (Method declaredMethod : declaredMethods) {
            System.out.println("方法：" + declaredMethod);
        }
        Field[] declaredField = clazz.getDeclaredFields();
        
        System.out.println("字段：" + declaredField);
        System.out.println(clazz);
    }
}
