package com.hoshino.springboot.starter.logback.autoconfigure;

import ch.qos.logback.classic.Level;
import ch.qos.logback.classic.Logger;
import ch.qos.logback.classic.LoggerContext;
import ch.qos.logback.classic.encoder.PatternLayoutEncoder;
import ch.qos.logback.classic.filter.ThresholdFilter;
import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.core.CoreConstants;
import ch.qos.logback.core.joran.spi.ConsoleTarget;
import ch.qos.logback.core.rolling.SizeAndTimeBasedRollingPolicy;
import ch.qos.logback.core.util.FileSize;
import ch.qos.logback.core.util.OptionHelper;
import com.hoshino.logback.appender.ConsoleSensitiveAppender;
import com.hoshino.logback.appender.RollingFileSensitiveAppender;
import com.hoshino.logback.sensitive.LoggingDesensitization;
import lombok.Data;
import net.logstash.logback.encoder.LogstashEncoder;
import org.apache.skywalking.apm.toolkit.log.logback.v1.x.logstash.SkyWalkingContextJsonProvider;
import org.apache.skywalking.apm.toolkit.log.logback.v1.x.logstash.TraceIdJsonProvider;
import org.slf4j.impl.StaticLoggerBinder;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * JavaConfig配置LoggerContext.
 * @author huangyuehao
 */
@Data
public class LogbackConfiguration{

    private final LoggerContext context = (LoggerContext) StaticLoggerBinder.getSingleton().getLoggerFactory();
    private final Logger root = context.getLogger(Logger.ROOT_LOGGER_NAME);
    private final Logger activitiLogging;
    private final Logger dubboLogging;
    private final LoggingDesensitization loggingDesensitization;
    private final LogbackContextProperties properties;

    public LogbackConfiguration (LoggingDesensitization loggingDesensitization, LogbackContextProperties logbackContextProperties) {
        this.loggingDesensitization = loggingDesensitization;
        this.properties = logbackContextProperties;
        activitiLogging = context.getLogger(properties.getLogger().get("activiti-logging").getName());
        dubboLogging = context.getLogger(properties.getLogger().get("dubbo-logging").getName());
    }

    private void base() {
        context.reset();

        Map<String, String> ruleRegistry = (Map<String, String>) context.getObject(CoreConstants.PATTERN_RULE_REGISTRY);
        if (ruleRegistry == null) {
            ruleRegistry = new HashMap<>();
        }
        // springboog logback default converter
        ruleRegistry.put("clr", "org.springframework.boot.logging.logback.ColorConverter");
        ruleRegistry.put("wex", "org.springframework.boot.logging.logback.WhitespaceThrowableProxyConverter");
        ruleRegistry.put("wEx", "org.springframework.boot.logging.logback.ExtendedWhitespaceThrowableProxyConverter");
        // SkyWalking
        ruleRegistry.put("tid", "org.springframework.boot.logging.logback.WhitespaceThrowableProxyConverter");
        ruleRegistry.put("sw_ctx", "org.springframework.boot.logging.logback.ExtendedWhitespaceThrowableProxyConverter");
        context.putObject(CoreConstants.PATTERN_RULE_REGISTRY, ruleRegistry);

        initLogger();
        initAppender();
    }

    private void initAppender() {
        // console appender
        ConsoleSensitiveAppender consoleAppender = new ConsoleSensitiveAppender();
        consoleAppender.setLoggingDesensitization(loggingDesensitization);
        consoleAppender.setContext(context);
        consoleAppender.setName(properties.getConsoleName());
        consoleAppender.setEncoder(patternLayoutEncoder(consoleAppender));
        consoleAppender.setOutputStream(ConsoleTarget.SystemOut.getStream());
        consoleAppender.start();
        root.addAppender(consoleAppender);
        dubboLogging.addAppender(consoleAppender);
        activitiLogging.addAppender(consoleAppender);

        // file appender
        Map<String, LogbackContextProperties.FileAppender> appenderMap = properties.getFileAppender();
        Set<String> keys = appenderMap.keySet();
        for (String key : keys) {
            LogbackContextProperties.FileAppender appenderProperties = appenderMap.get(key);

            RollingFileSensitiveAppender rollingFileAppender = new RollingFileSensitiveAppender();
            rollingFileAppender.setLoggingDesensitization(loggingDesensitization);
            rollingFileAppender.setContext(context);
            rollingFileAppender.setName(appenderProperties.getName());
            rollingFileAppender.setFile(appenderProperties.getPath());
            rollingFileAppender.setRollingPolicy(rollingPolicy(rollingFileAppender, appenderProperties.getNamePattern()));
            rollingFileAppender.setEncoder(getLogstashEncoder());
            rollingFileAppender.setAppend(true);
            rollingFileAppender.start();

            ThresholdFilter errorFilter = new ThresholdFilter();
            errorFilter.setLevel(appenderProperties.getFilterLevel());
            errorFilter.start();

            rollingFileAppender.addFilter(errorFilter);
            rollingFileAppender.setAppend(true);
            rollingFileAppender.start();

            String toLoggers = appenderProperties.getToLoggers();
            if (toLoggers != null) {
                String[] loggers = appenderProperties.getToLoggers().split(",");
                for (String logger : loggers) {
                    this.context.getLogger(logger).addAppender(rollingFileAppender);
                }
            }
        }
    }

    private void initLogger() {
        Map<String, LogbackContextProperties.Logger> loggerMap = properties.getLogger();
        Set<String> keys = loggerMap.keySet();
        for (String key : keys) {
            LogbackContextProperties.Logger loggerProperties = loggerMap.get(key);
            Logger logger = this.context.getLogger(loggerProperties.getName());
            String level = loggerProperties.getLevel();
            if (level != null) {
                logger.setLevel(Level.valueOf(level));
            }
            Boolean isAdditive = loggerProperties.getIsAdditive();
            if (isAdditive != null) {
                logger.setAdditive(isAdditive);
            }
        }
    }

    private PatternLayoutEncoder patternLayoutEncoder(ConsoleSensitiveAppender consoleAppender) {
        PatternLayoutEncoder patternLayoutEncoder = new PatternLayoutEncoder();
        patternLayoutEncoder.setContext(context);
        patternLayoutEncoder.setPattern(OptionHelper.substVars(properties.getConsolePattern(), context));
        patternLayoutEncoder.setParent(consoleAppender);
        patternLayoutEncoder.setOutputPatternAsHeader(false);
        patternLayoutEncoder.start();
        return patternLayoutEncoder;
    }

    private SizeAndTimeBasedRollingPolicy<ILoggingEvent> rollingPolicy(RollingFileSensitiveAppender appender,
                                                                       String fileNamePattern) {
        SizeAndTimeBasedRollingPolicy<ILoggingEvent> rollingPolicy = new SizeAndTimeBasedRollingPolicy<>();
        rollingPolicy.setContext(context);
        rollingPolicy.setFileNamePattern(fileNamePattern);
        rollingPolicy.setMaxFileSize(FileSize.valueOf(properties.getFileMaxSize()));
        rollingPolicy.setMaxHistory(properties.getFileMaxHistory());
        rollingPolicy.setTotalSizeCap(new FileSize(properties.getFileTotalSizeCap()));
        rollingPolicy.setParent(appender);
        rollingPolicy.start();
        return rollingPolicy;
    }

    private LogstashEncoder getLogstashEncoder() {
        LogstashEncoder logstashEncoder = new LogstashEncoder();
        logstashEncoder.setContext(context);
        logstashEncoder.addProvider(new TraceIdJsonProvider());
        logstashEncoder.addProvider(new SkyWalkingContextJsonProvider());
        logstashEncoder.setShortenedLoggerNameLength(properties.getShortenedLoggerNameLength());
        logstashEncoder.setTimestampPattern(properties.getTimestampPattern());
        List<String> mdcKeys = properties.getMdcKeyNames();
//        String[] mdcKeys = properties.getMdcKeyNames().split(",");
        for (String key : mdcKeys) {
            logstashEncoder.addIncludeMdcKeyName(key);
        }
        logstashEncoder.start();
        return logstashEncoder;
    }

}
