package com.hoshino.study.maven.utils;

/**
 * @author huangyuehao
 * @date 2022-09-01
 */

import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;
import com.hoshino.study.maven.domain.Users;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 1.将JavaBean转换为json，或将json字符串转换为JavaBean。
 * 2.将List集合转换为json，或将json转换为List集合。
 * 3.将Map集合转换为json，或将json转换为Map集合。
 */
public class MyGson {

    public static void gson1() {
        Users users = new Users(001, "艾伦", "2000", 18);
        System.out.println("users: " + users);

        Gson gson = new Gson();
        String json = gson.toJson(users);
        System.out.println("Gson-toJson: " + json);

        Users fromJson = gson.fromJson(json, Users.class);
        System.out.println("Gson-fromJson: " + fromJson);
    }

    public static void gson2() {
        Users users1 = new Users(001, "艾伦", "2000", 18);
        Users users2 = new Users(002, "三立", "1000", 16);

        List<Users> usersList = new ArrayList<>();
        usersList.add(users1);
        usersList.add(users2);
        System.out.println("usersList: " + usersList);

        Gson gson = new Gson();
        String json = gson.toJson(usersList);
        System.out.println("Gson-toJson: " + json);

        Type type = new TypeToken<List<Users>>(){}.getType();
        List<Users> fromJson = gson.fromJson(json, type);
        System.out.println("Gson-fromJson: " + fromJson);

    }

    public static void gson3() {
        Map<String, Users> usersMap = new HashMap<>();
        Users users1 = new Users(001, "艾伦", "2000", 18);
        Users users2 = new Users(002, "三立", "1000", 16);
        usersMap.put("p1", users1);
        usersMap.put("p2", users2);
        System.out.println("usersMap: " + usersMap);

        Gson gson = new Gson();
        String json = gson.toJson(usersMap);
        System.out.println("Gson-toJson: " + json);

        Type type = new TypeToken<Map<String, Users>>(){}.getType();
        Map<String, Users> fromJson = gson.fromJson(json, type);
        System.out.println("Gson-fromJson: " + fromJson);
    }

    public static void main (String[] args) {
        gson1();
        System.out.println("--------------");
        gson2();
        System.out.println("--------------");
        gson3();
    }
}
