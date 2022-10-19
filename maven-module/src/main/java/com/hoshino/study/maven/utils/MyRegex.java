package com.hoshino.study.maven.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author huangyuehao
 * @date 2022-08-03
 */
public class MyRegex {
    public static void main (String[] args) {
        String str = "";
        String pattern = "@-@";

        Pattern r = Pattern.compile(pattern);
        System.out.println(r);
        Matcher m = r.matcher(str);
        System.out.println(m.matches());
    }
}
