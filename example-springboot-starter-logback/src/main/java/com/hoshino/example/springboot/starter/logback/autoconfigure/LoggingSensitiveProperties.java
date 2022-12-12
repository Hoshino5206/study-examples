package com.hoshino.example.springboot.starter.logback.autoconfigure;

import com.hoshino.example.logback.sensitive.LoggingSensitive;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.Map;

/**
 * LoggingSensitive Properties
 * @author huangyuehao
 */
@Data
@ConfigurationProperties(prefix = LoggingSensitiveProperties.PREFIX)
public class LoggingSensitiveProperties {

    public static final String PREFIX = "ch.qos.logback.sensitive";

    private Boolean enable = false;

    private Map<String, Map<String, LoggingSensitive>> logs;

}

