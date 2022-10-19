package com.hoshino.study.logback.encoder;

import ch.qos.logback.classic.encoder.PatternLayoutEncoder;
import com.hoshino.study.logback.layout.LogbackPatternLayout;
import com.hoshino.study.logback.regex.RegexReplacementList;

/**
 * @author huangyuehao
 * @date 2022-09-22
 */
public class LogbackPatternLayoutEncoder extends PatternLayoutEncoder {

    /**
     * 正则替换规则
     */
    private RegexReplacementList replaces;
    /**
     * 是否开启脱敏，默认关闭(false）
     */
    private Boolean sensitive = false;

    /**
     * 使用自定义TbspLogbackPatternLayout格式化输出
     */
    @Override
    public void start() {
        LogbackPatternLayout patternLayout = new LogbackPatternLayout(replaces, sensitive);
        patternLayout.setContext(context);
        patternLayout.setPattern(this.getPattern());
        patternLayout.setOutputPatternAsHeader(outputPatternAsHeader);
        patternLayout.start();
        this.layout = patternLayout;
        started = true;
    }

    public boolean isSensitive() {
        return sensitive;
    }

    public void setSensitive(boolean sensitive) {
        this.sensitive = sensitive;
    }

    public RegexReplacementList getReplaces() {
        return replaces;
    }

    public void setReplaces(RegexReplacementList replaces) {
        this.replaces = replaces;
    }

}
