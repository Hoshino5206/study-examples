package com.hoshino.study.logback.converter;

/**
 * logback日志脱敏类型
 * @author huangyuehao
 */
public class LogbackSensitiveType {

    /**
     * 字符串全脱敏
     */
    public static final String FULL = "full";

    /**
     * 字符串范围脱敏
     */
    public static final String RANGE = "range";

    /**
     * 字符串正则匹配脱敏
     */
    public static final String REGEX = "regex";

}
