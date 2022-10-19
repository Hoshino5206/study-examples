package com.hoshino.study.logback.util;

import com.hoshino.study.logback.converter.LogbackSensitive;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

/**
 * logback日志脱敏工具类
 * @author huangyuehao
 */
public class PropertiesUtil {

    private static final Logger LOGGER = LoggerFactory.getLogger(PropertiesUtil.class);

    private static final Map<String, LogbackSensitive> sensitiveMap = new HashMap<>();

    private static final String PROPERTY_SOURCE_PATH = "com/hoshino/study/logback/default.properties";

    /**
     * 读取logback配置文件default.properties
     * @return
     */
    public static Map<String, LogbackSensitive> getResource() throws Exception {
        Properties properties = new Properties();
        try {
            InputStream inputStream = PropertiesUtil.class.getClassLoader().getResourceAsStream(PROPERTY_SOURCE_PATH);
            properties.load(inputStream);
            inputStream.close();
        } catch (IOException e) {
            LOGGER.warn("load properties error from the path: {}", PROPERTY_SOURCE_PATH);
        }

        Set<Object> propertiesKeys = properties.keySet();
        for (Object propertyKey : propertiesKeys) {
            String sensitiveValue = properties.getProperty((String) propertyKey);
            // 配置文件key值以"."分割转换为数组，如logback.replaces.cardNo.replacement
            // 转换成["kael", "logback", "sensitive", "replaces", "cardNo", replacement]
            String[] propertyKeyToList = propertyKey.toString().split("\\.");
            // 需要脱敏的关键字sensitiveKey
            String sensitiveKey = propertyKeyToList[2];
            // 日志脱敏对象LogbackSensitive属性
            String sensitiveFiled = propertyKeyToList[3];

            LogbackSensitive logbackSensitive = new LogbackSensitive();
            Set<String> fieldNameSet = getFieldNameSet(logbackSensitive);

            // 如果sensitiveMap已经存在key，则直接对value进行更新
            if (fieldNameSet.contains(sensitiveFiled)) {
                if (sensitiveMap.containsKey(sensitiveKey)) {
                    logbackSensitive = sensitiveMap.get(sensitiveKey);
                    setField(logbackSensitive, sensitiveFiled, sensitiveValue);
                    sensitiveMap.remove(sensitiveKey);
                } else {
                    setField(logbackSensitive, sensitiveFiled, sensitiveValue);
                }
                sensitiveMap.put(sensitiveKey, logbackSensitive);
            }
        }
        return sensitiveMap;
    }

    private static Set<String> getFieldNameSet(Object obj) {
        Field[] declaredFields = obj.getClass().getDeclaredFields();
        return Arrays.stream(declaredFields)
                .map(Field::getName)
                .collect(Collectors.toSet());
    }

    private static void setField(Object obj, String field, String value) throws Exception {
        Field[] declaredFields = obj.getClass().getDeclaredFields();
        for (Field declaredField : declaredFields) {
            if (field.equals(declaredField.getName())) {
                declaredField.setAccessible(true);
                declaredField.set(obj, value);
            }
        }
    }

    /**
     * 编译正则表达式并实例化Pattern对象
     * @param regex
     * @return
     */
    public static Pattern regexFormat(String regex) {
        return Pattern.compile(regex);
    }

    /**
     * 匹配字符串，生成匹配对象
     * @param regex
     * @param msg
     * @return
     */
    public static Matcher matcher(String regex, String msg) {
        return regexFormat(regex).matcher(msg);
    }

    /**
     * 字符串替换脱敏
     * @param regex
     * @param msg
     * @param replacement
     * @return
     */
    public static String msgSensitive(String regex, String msg, String replacement) {
        return matcher(regex, msg).replaceAll(replacement);
    }

}
