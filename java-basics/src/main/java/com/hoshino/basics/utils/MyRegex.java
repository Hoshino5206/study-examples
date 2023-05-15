package com.hoshino.basics.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author huangyuehao
 * @date 2022-08-03
 */
public class MyRegex {
    public static void main (String[] args) {
        String regex = "(cardNo)(=|=\\[|\\\":\\\"|:|: |：|='|':')(\\d{4})(\\d{11})(\\d{4})(\\]|\\\"|'|)";
        String str = "cardNo:6602934033997686009，身份证号:6602934033997686009 ";
        String replacement = "$1$2$3***********$5$6";

        Pattern pattern = Pattern.compile(regex);
        System.out.println(pattern);
        Matcher matcher = pattern.matcher(str);
        System.out.println(matcher);
        String replaceAll = matcher.replaceAll(replacement);
        System.out.println("replaceAll = " + replaceAll);
    }
}
