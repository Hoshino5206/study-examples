package com.hoshino.basics.reflect;

import com.hoshino.basics.domain.Users;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * @author huangyuehao
 * @date 2022-09-15
 */
public class MyReflect {
    public static void main (String[] args) throws Exception {
        // 第一种 Class.forName("类的全路径");
        // 第二种 Class clazz = xxx.class;
        // 第三种 User user = new User(); Class clazz = user.getClass();
        Users user = new Users();
        Class<? extends Users> clazz = user.getClass();
        System.out.println("clazz = " + clazz);

        // 获取完整类名
        String fullClassName = clazz.getName();
        System.out.println("fullClassName = " + fullClassName);
        // 获取包名
        String packName = clazz.getPackage().getName();
        System.out.println("packName = " +packName);
        // 获取类名
        String className = clazz.getSimpleName();
        System.out.println("className = " + className);

        // 获取类中所有公共构造方法，包括继承自父类的
        Constructor<?>[] constructors = clazz.getConstructors();
        for (Constructor<?> constructor : constructors) {
            System.out.println("constructor = " + constructor);
        }

        // 获取类中的所有构造方法
        Constructor<?>[] declaredConstructors = clazz.getDeclaredConstructors();
        for (Constructor<?> declaredConstructor : declaredConstructors) {
            System.out.println("declaredConstructor = " + declaredConstructor);
        }

        // 获取类中所有公共方法，包括继承自父类的
        Method[] methods = clazz.getMethods();
        for (Method method : methods) {
            System.out.println("method = " + method);
        }

        // 获取类中自己声明的所有方法（包括私有的、保护的）
        Method[] declaredMethods = clazz.getDeclaredMethods();
        for (Method declaredMethod : declaredMethods) {
            System.out.println("declaredMethod = " + declaredMethod);
        }

        // 获取类中所有公共字段，包括继承自父类的
        Field[] fields = clazz.getFields();
        for (Field field : fields) {
            System.out.println("field = " + field);
        }

        // 获取类中自己声明的所有字段（包括私有的、保护的）
        Field[] declaredFields = clazz.getDeclaredFields();
        for (Field declaredField : declaredFields) {
            System.out.println("declaredField = " + declaredField);
        }

        // 反射获取实例
        Users instance = clazz.newInstance();

        // 反射调用成员变量
        Field username = clazz.getDeclaredField("username");
        username.setAccessible(true);
        username.set(user, "张三");
        System.out.println(user);

        // 反射调用成员方法
        Method haha = clazz.getDeclaredMethod("haha", String.class);
        haha.setAccessible(true);
        haha.invoke(user, "李四");

    }
}
