package com.hoshino.example.maven.dataType;

/**
 * @author huangyuehao
 * @date 2022-08-03
 */
public class MyString {

    private final static String SSS = "abcd";

    public static void main(String[] args) {
        System.out.println(SSS + "EFG");

        String fullName = "file-gateway/fileCheckJob";
        int index = fullName.indexOf("/");
        System.out.println(index);
        String appName = fullName.substring(0, index);
        System.out.println(appName);
        String jobName = fullName.substring(index+1);
        System.out.println(jobName);
    }

}
