package com.hoshino.springboot.starter.logback.autoconfigure;

import com.hoshino.logback.sensitive.LoggingDesensitization;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

/**
 * LoggingSensitive AutoConfiguration
 * @author huangyuehao
 */
@Configuration
@EnableConfigurationProperties({LoggingSensitiveProperties.class, LogbackContextProperties.class})
@ConditionalOnClass({LoggingDesensitization.class, LogbackConfiguration.class})
@ConditionalOnProperty(prefix = LoggingSensitiveProperties.PREFIX, name = "enable", havingValue = "true")
@PropertySource(value = "classpath:com/hoshino/springboot/starter/logback/default.properties", encoding = "UTF-8")
public class LoggingSensitiveAutoConfiguration {

    private final LogbackContextProperties contextProperties;
    private final LoggingSensitiveProperties sensitiveProperties;

    public LoggingSensitiveAutoConfiguration (LogbackContextProperties contextProperties, LoggingSensitiveProperties sensitiveProperties) {
        this.contextProperties = contextProperties;
        this.sensitiveProperties = sensitiveProperties;
    }

    @Bean(initMethod = "init")
    @ConditionalOnMissingBean
    public LogbackConfiguration logbackConfiguration() {
        return new LogbackConfiguration(contextProperties, sensitiveProperties);
    }
}
