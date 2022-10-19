package com.hoshino.study.maven.utils;

import org.apache.commons.lang3.StringUtils;

/**
 * @author huangyuehao
 * @date 2022-09-06
 */
public class MyStringUtil {

    public static void main(String[] args) {

        String path = "/gw/dubbo/cif/query/request";
        int index = StringUtils.indexOf(path, "/", 4);
        System.out.println(index);
    }

}
