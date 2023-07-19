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
import org.springframework.util.CollectionUtils;

import java.util.*;

/**
 * JavaConfig配置LoggerContext.
 * @author huangyuehao
 */
@Data
public class LogbackConfiguration {

    private final LoggerContext context = (LoggerContext) StaticLoggerBinder.getSingleton().getLoggerFactory();

    private final LogbackContextProperties contextProperties;

    private final LoggingSensitiveProperties sensitiveProperties;

    private final LoggingDesensitization loggingDesensitization;

    private final LoggingDesensitization emptyMapLoggingDes;

    public LogbackConfiguration (LogbackContextProperties contextProperties,
                                 LoggingSensitiveProperties sensitiveProperties) {
        this.contextProperties = contextProperties;
        this.sensitiveProperties = sensitiveProperties;

        this.loggingDesensitization = new LoggingDesensitization(sensitiveProperties.getRules());
        this.emptyMapLoggingDes = new LoggingDesensitization(Collections.emptyMap());
    }

    private void init() {
        this.context.reset();

        Map<String, String> ruleRegistry = (Map<String, String>) context.getObject(CoreConstants.PATTERN_RULE_REGISTRY);
        if (ruleRegistry == null) {
            ruleRegistry = new HashMap<>(8);
        }
        // springboog logback default converter
        ruleRegistry.put("clr", "org.springframework.boot.logging.logback.ColorConverter");
        ruleRegistry.put("wex", "org.springframework.boot.logging.logback.WhitespaceThrowableProxyConverter");
        ruleRegistry.put("wEx", "org.springframework.boot.logging.logback.ExtendedWhitespaceThrowableProxyConverter");
        // SkyWalking
        ruleRegistry.put("tid", "org.apache.skywalking.apm.toolkit.log.logback.v1.x.logstash.TraceIdJsonProvider");
        ruleRegistry.put("sw_ctx", "org.apache.skywalking.apm.toolkit.log.logback.v1.x.logstash.SkyWalkingContextJsonProvider");
        this.context.putObject(CoreConstants.PATTERN_RULE_REGISTRY, ruleRegistry);

        initLogger();
        initAppender();
    }

    private void initLogger() {
        Map<String, LogbackContextProperties.Logger> loggerMap = contextProperties.getLogger();
        for (Map.Entry<String, LogbackContextProperties.Logger> loggerEntry : loggerMap.entrySet()) {
            LogbackContextProperties.Logger loggerProperties = loggerEntry.getValue();

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

    private void initAppender() {
        // console appender
        ConsoleSensitiveAppender consoleAppender = new ConsoleSensitiveAppender();
        consoleAppender.setContext(context);
        consoleAppender.setName(contextProperties.getConsoleAppender().getName());
        consoleAppender.setEncoder(buildLayoutEncoder(consoleAppender));
        consoleAppender.setOutputStream(ConsoleTarget.SystemOut.getStream());
        consoleAppender.start();

        List<String> loggers = contextProperties.getConsoleAppender().getToLoggers();
        if (!CollectionUtils.isEmpty(loggers)) {
            for (String loggerName : loggers) {
                List<String> filter = sensitiveProperties.getFilter();
                // 过滤不需要脱敏的logger
                if (!CollectionUtils.isEmpty(filter) && filter.contains(loggerName)) {
                    consoleAppender.setLoggingDesensitization(emptyMapLoggingDes);
                } else {
                    consoleAppender.setLoggingDesensitization(loggingDesensitization);
                }
                this.context.getLogger(loggerName).addAppender(consoleAppender);
            }
        }

        // file appender
        Map<String, LogbackContextProperties.FileAppender> appenderMap = contextProperties.getFileAppender();
        for (Map.Entry<String, LogbackContextProperties.FileAppender> appenderEntry : appenderMap.entrySet()) {
            LogbackContextProperties.FileAppender appenderProperties = appenderEntry.getValue();

            RollingFileSensitiveAppender rollingFileAppender = new RollingFileSensitiveAppender();
            rollingFileAppender.setLoggingDesensitization(loggingDesensitization);
            rollingFileAppender.setContext(context);
            rollingFileAppender.setName(appenderProperties.getName());
            rollingFileAppender.setFile(appenderProperties.getPath());
            rollingFileAppender.setRollingPolicy(buildRollingPolicy(rollingFileAppender, appenderProperties.getNamePattern()));
            rollingFileAppender.setEncoder(buildLogstashEncoder());

            ThresholdFilter thresholdFilter = new ThresholdFilter();
            thresholdFilter.setLevel(appenderProperties.getFilterLevel());
            thresholdFilter.start();

            rollingFileAppender.addFilter(thresholdFilter);
            rollingFileAppender.setAppend(true);
            rollingFileAppender.start();

            List<String> toLoggers = appenderProperties.getToLoggers();
            if (!CollectionUtils.isEmpty(toLoggers)) {
                for (String loggerName : toLoggers) {
                    List<String> filter = sensitiveProperties.getFilter();
                    if (!CollectionUtils.isEmpty(filter) && filter.contains(loggerName)) {
                        rollingFileAppender.setLoggingDesensitization(emptyMapLoggingDes);
                    } else {
                        rollingFileAppender.setLoggingDesensitization(loggingDesensitization);
                    }
                    this.context.getLogger(loggerName).addAppender(rollingFileAppender);
                }
            }
        }
    }

    private PatternLayoutEncoder buildLayoutEncoder(ConsoleSensitiveAppender consoleAppender) {
        PatternLayoutEncoder patternLayoutEncoder = new PatternLayoutEncoder();
        patternLayoutEncoder.setContext(context);
        patternLayoutEncoder.setPattern(OptionHelper.substVars(contextProperties.getConsoleAppender().getPattern(), context));
        patternLayoutEncoder.setParent(consoleAppender);
        patternLayoutEncoder.setOutputPatternAsHeader(false);
        patternLayoutEncoder.start();
        return patternLayoutEncoder;
    }

    private SizeAndTimeBasedRollingPolicy<ILoggingEvent> buildRollingPolicy(RollingFileSensitiveAppender appender,
                                                                            String fileNamePattern) {
        SizeAndTimeBasedRollingPolicy<ILoggingEvent> rollingPolicy = new SizeAndTimeBasedRollingPolicy<>();
        rollingPolicy.setContext(context);
        rollingPolicy.setFileNamePattern(fileNamePattern);
        rollingPolicy.setMaxFileSize(FileSize.valueOf(contextProperties.getFileMaxSize()));
        rollingPolicy.setMaxHistory(contextProperties.getFileMaxHistory());
        rollingPolicy.setTotalSizeCap(new FileSize(contextProperties.getFileTotalSizeCap()));
        rollingPolicy.setParent(appender);
        rollingPolicy.start();
        return rollingPolicy;
    }

    private LogstashEncoder buildLogstashEncoder() {
        LogstashEncoder logstashEncoder = new LogstashEncoder();
        logstashEncoder.setContext(context);
        logstashEncoder.addProvider(new TraceIdJsonProvider());
        logstashEncoder.addProvider(new SkyWalkingContextJsonProvider());
        logstashEncoder.setShortenedLoggerNameLength(contextProperties.getShortenedLoggerNameLength());
        logstashEncoder.setTimestampPattern(contextProperties.getTimestampPattern());

        List<String> mdcKeyNames = contextProperties.getMdcKeyNames();
        for (String mdcKeyName : mdcKeyNames) {
            logstashEncoder.addIncludeMdcKeyName(mdcKeyName);
        }

        logstashEncoder.start();
        return logstashEncoder;
    }
}
