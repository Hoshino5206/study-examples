package com.hoshino.study.maven.jdk8;

import com.hoshino.study.maven.domain.Users;
import org.apache.commons.lang3.StringUtils;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author huangyuehao
 * @date 2022-07-22
 */
public class MyStream {
    public static void main(String[] args) {
        int[] arr = {1, 2, 3};

        Users users1 = new Users(1, "tom", "200", 20);
        Users users2 = new Users(2, "zhangsan", "300", 30);
        Users users3 = new Users(3, "lisi", "400", 40);

        List<Users> usersList = Stream.of(users1, users2, users3).collect(Collectors.toList());
        List<Integer> collectFilter = usersList.stream().map(Users :: getId).collect(Collectors.toList());
        System.out.println(collectFilter);

        String str1 = "helloWorld";
        String str2 = "hahaha";
        String str = "";
        System.out.println(String.format("%s/%s", "sasxa", "dqwdq"));
        System.out.println(StringUtils.isBlank(str));
    }
}