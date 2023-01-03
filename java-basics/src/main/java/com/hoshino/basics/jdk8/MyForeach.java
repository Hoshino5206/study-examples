package com.hoshino.basics.jdk8;

import com.hoshino.basics.domain.Users;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author huangyuehao
 * @date 2022-08-30
 */
public class MyForeach {
    public static void main (String[] args) {
        Users users1 = new Users(1, "aaa", "123", 20);
        Users users2 = new Users(2, "bbb", "345", 27);
        Users users3 = new Users(3, "ccc", "567", 29);
        List<Users> usersList = Stream.of(users1, users2, users3).collect(Collectors.toList());

        usersList.forEach(users -> {
            System.out.println(users.getUsername());
        });

    }
}
