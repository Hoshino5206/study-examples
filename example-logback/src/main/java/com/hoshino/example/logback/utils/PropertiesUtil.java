package com.hoshino.example.logback.utils;

import com.hoshino.example.logback.constant.SensitiveTypeConstant;
import com.hoshino.example.logback.sensitive.LogbackSensitive;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.util.*;
import java.util.stream.Collectors;

/**
 * logback配置文件读取工具
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
        InputStream inputStream = null;
        try {
            inputStream = PropertiesUtil.class.getClassLoader().getResourceAsStream(PROPERTY_SOURCE_PATH);
            properties.load(inputStream);
        } catch (IOException e) {
            LOGGER.error("load properties error from the path: {}", PROPERTY_SOURCE_PATH);
        } finally {
            inputStream.close();
        }

        Set<Object> propertiesKeys = properties.keySet();
        for (Object propertyKey : propertiesKeys) {
            String sensitiveValue = properties.getProperty((String) propertyKey);
            // 配置文件key值以"."分割转换为数组，如logback.replaces.regex.phone.regex
            // 转换成["如logback", "replaces", "regex", "phone", "regex"]
            String[] propertyKeyToList = propertyKey.toString().split("\\.");
            boolean isHasPrefix = StringUtils.startsWith((String)propertyKey, "logback.replaces.");
            if(!isHasPrefix) {
                LOGGER.error("The key prefix must be starts with [logback.replaces], please check the property:[{}]", propertyKey);
                break;
            }
            // 脱敏类型
            String sensitiveType = propertyKeyToList[2];
            // 需要脱敏的关键字sensitiveKey
            String sensitiveKey = propertyKeyToList[3];
            // 日志脱敏对象LogbackSensitive属性
            String sensitiveFiled = propertyKeyToList[4];

            LogbackSensitive logbackSensitive = new LogbackSensitive();
            Set<String> fieldNameSet = getFieldNameSet(logbackSensitive);

            // 如果sensitiveMap已经存在key，则直接对value进行更新
            if (fieldNameSet.contains(sensitiveFiled) &&
                    (SensitiveTypeConstant.REGEX.equals(sensitiveType) || SensitiveTypeConstant.KV.equals(sensitiveType))) {
                if (sensitiveMap.containsKey(sensitiveKey)) {
                    logbackSensitive = sensitiveMap.get(sensitiveKey);
                    setField(logbackSensitive, sensitiveFiled, sensitiveValue);
                    sensitiveMap.remove(sensitiveKey);
                } else {
                    logbackSensitive.setType(sensitiveType);
                    setField(logbackSensitive, sensitiveFiled, sensitiveValue);
                }
                sensitiveMap.put(sensitiveKey, logbackSensitive);
            } else {
                LOGGER.error("The type of desensitization is not support or The desensitization field is incorrect");
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

}
