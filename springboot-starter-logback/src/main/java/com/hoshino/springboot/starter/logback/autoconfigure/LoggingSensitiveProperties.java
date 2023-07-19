package com.hoshino.springboot.starter.logback.autoconfigure;

import com.hoshino.logback.sensitive.LoggingSensitive;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.List;
import java.util.Map;

/**
 * LoggingSensitive Properties
 * @author huangyuehao
 */
@Data
@ConfigurationProperties(prefix = LoggingSensitiveProperties.PREFIX)
public class LoggingSensitiveProperties {

    public static final String PREFIX = "com.hoshino.logging.sensitive";

    private Boolean enable = false;

    private Map<String, Map<String, LoggingSensitive>> rules;

}


