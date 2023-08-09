package com.hoshino.basics.dataType;

/**
 * @author huangyuehao
 * @date 2022-08-03
 */
public class MyString {

    public static void main(String[] args) {
        String s1 = "Hello, World!";

        // 字符串长度
        int length = s1.length();
        System.out.println("length: " + length);

        // 查找指定坐标的字符
        char charAt = s1.charAt(2);
        System.out.println("charAt: " + charAt);

        // 返回索引到的位置从 下标0开始  找不到返回-1
        int indexOf = s1.indexOf("l");
        System.out.println("indexOf: " + indexOf);

        // 判断字符是否为空
        boolean empty = s1.isEmpty();
        System.out.println("empty: " + empty);

        // 判断字符串中是否存在某个字符
        boolean contains = s1.contains("o");
        System.out.println("contains: " + contains);

        // 去掉前面的空格和后面的空格
        String trim = s1.trim();
        System.out.println("trim: " + trim);

        // 截取字符串
        String substring = s1.substring(2);
        String substring1 = s1.substring(2, 4);
        System.out.println("substring: " + substring);
        System.out.println("substring1: " + substring1);

        // 用新字符替换字符串的所有旧字符
        String replace = s1.replace("l", "a");
        System.out.println("replace: " + replace);

        // 字符串转换大小写
        String lowerCase = s1.toLowerCase();
        String upperCase = s1.toUpperCase();
        System.out.println("lowerCase: " + lowerCase);
        System.out.println("upperCase: " + upperCase);

        // string转char
        char[] chars = s1.toCharArray();
        System.out.println("chars[5]: " + chars[5]);

        // char转string
        char[] chars1 = new char[]{'1','2','a','b','c'};
        String charString = new String(chars1);
        System.out.println("charString: " + charString);
    }

}
