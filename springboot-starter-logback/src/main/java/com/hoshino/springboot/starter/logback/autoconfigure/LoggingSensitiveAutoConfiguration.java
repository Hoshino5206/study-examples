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
@PropertySource(value = "classpath:com/hoshino/logback/default.properties", encoding = "UTF-8")
public class LoggingSensitiveAutoConfiguration {

    private final LoggingSensitiveProperties sensitiveProperties;
    private final LogbackContextProperties contextProperties;

    public LoggingSensitiveAutoConfiguration (LoggingSensitiveProperties sensitiveProperties,
                                              LogbackContextProperties contextProperties) {
        this.sensitiveProperties = sensitiveProperties;
        this.contextProperties = contextProperties;
    }

    @Bean(initMethod = "base")
    @ConditionalOnMissingBean
    public LogbackConfiguration logbackConfiguration() {
        return new LogbackConfiguration(new LoggingDesensitization(sensitiveProperties.getLogs()), contextProperties);
    }

}
