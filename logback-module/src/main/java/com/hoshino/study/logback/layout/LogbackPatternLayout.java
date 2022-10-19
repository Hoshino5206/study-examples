package com.hoshino.study.logback.layout;

import ch.qos.logback.classic.PatternLayout;
import ch.qos.logback.classic.spi.ILoggingEvent;
import com.hoshino.study.logback.regex.RegexReplacement;
import com.hoshino.study.logback.regex.RegexReplacementList;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author huangyuehao
 * @date 2022-09-22
 */
public class LogbackPatternLayout extends PatternLayout {
    /**
     * logger
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(LogbackPatternLayout.class);
    /**
     * 正则替换规则
     */
    private RegexReplacementList replaces;
    /**
     * 是否开启脱敏，默认关闭(false）
     */
    private Boolean sensitive;

    public LogbackPatternLayout(RegexReplacementList replaces, Boolean sensitive) {
        super();
        this.replaces = replaces;
        this.sensitive = sensitive;
    }

    /**
     * 格式化日志信息
     *
     * @param event ILoggingEvent
     * @return 日志信息
     */
    @Override
    public String doLayout(ILoggingEvent event) {
        // 占位符填充
        String msg = super.doLayout(event);
        // 脱敏处理
        return this.buildSensitiveMsg(msg);
    }

    /**
     * 根据配置对日志进行脱敏
     *
     * @param msg 消息
     * @return 脱敏后的日志信息
     */
    public String buildSensitiveMsg(String msg) {
        if (sensitive == null || !sensitive) {
            // 未开启脱敏
            return msg;
        }
        if (this.replaces == null || this.replaces.getReplace() == null || this.replaces.getReplace().isEmpty()) {
            LOGGER.error("日志脱敏开启，但未配置脱敏规则，请检查配置后重试");
            return msg;
        }

        String sensitiveMsg = msg;

        for (RegexReplacement replace : this.replaces.getReplace()) {
            // 遍历脱敏正则 & 替换敏感数据
            sensitiveMsg = replace.format(sensitiveMsg);
        }
        return sensitiveMsg;
    }

}
