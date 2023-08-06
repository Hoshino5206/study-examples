package com.hoshino.algorithm.替换空格;

/**
 * @author Yy_hoshino
 * @date 2022-01-11 16:05
 */

/**
 * 请实现一个函数，把字符串 s 中的每个空格替换成"%20"。
 */
public class replaceSpace {
    public String replaceSpace(String s) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < s.length()-1; i++) {
            char ch = s.charAt(i);
            if (ch == ' ') sb.append("%20");
            else sb.append(ch);
        }
        return sb.toString();
    }
}
