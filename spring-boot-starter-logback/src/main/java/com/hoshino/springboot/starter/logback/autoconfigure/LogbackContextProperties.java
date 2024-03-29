package com.hoshino.springboot.starter.logback.autoconfigure;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.List;
import java.util.Map;

/**
 * LogbackContext Properties
 * @author huangyuehao
 */
@Data
@ConfigurationProperties(prefix = LogbackContextProperties.PREFIX)
public class LogbackContextProperties {

    public static final String PREFIX = "com.hoshino.logback.context";

    private String fileMaxSize = "200MB";

    private int fileMaxHistory = 7;

    private long fileTotalSizeCap = 0;

    private Integer shortenedLoggerNameLength = 30;

    private String timestampPattern = "yyyy-MM-dd HH:mm:ss.SSS";

    private List<String> mdcKeyNames;

    private Map<String, Logger> logger;

    private ConsoleAppender consoleAppender;

    private Map<String, FileAppender> fileAppender;

    @Data
    public static class ConsoleAppender {

        private String name;

        private String pattern;

        private List<String> toLoggers;
    }

    @Data
    public static class FileAppender {

        private String name;

        private String path;

        private String namePattern;

        private String filterLevel;

        private List<String> toLoggers;
    }

    @Data
    public static class Logger {

        private String name;

        private String level = "DEBUG";

        private Boolean isAdditive = true;
    }
}
