package com.hoshino.basics.utils;

import com.hoshino.basics.domain.Users;

import java.util.*;

/**
 * @author huangyuehao
 * @date 2022-09-07
 */
public class MyIterator {
    public static void main (String[] args) {
        Users users1 = new Users(001, "艾伦", "2000", 18);
        Users users2 = new Users(002, "三立", "1000", 16);

        List<Users> usersList = new ArrayList<>();
        usersList.add(users1);
        usersList.add(users2);

        Iterator<Users> iterator = usersList.iterator();
//        for (; iterator.hasNext();) {
//            Users users = iterator.next();
//            users.setAge(5000);
//        }
        while (iterator.hasNext()) {
            Users users = iterator.next();
            if (users.getPassword().equals("1000")) {
                users.setAge(50);
            }
        }
        System.out.println(usersList);

    }
}
