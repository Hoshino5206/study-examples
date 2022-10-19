package com.hoshino.study.logback.converter;

/**
 * sensitiveProperty
 * @author huangyuehao
 */
public class LogbackSensitive {

    /**
     * sensitiveType(full:全脱敏, range:范围脱敏, regex:正则脱敏)
     */
    private String type;

    /**
     * example: 4,7
     * the beginning index, inclusive.
     * the ending index, exclusive.
     */
    private String position;

    /**
     * regex pattern
     */
    private String regex;

    /**
     * replace CharSequence
     */
    private String replacement;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getRegex() {
        return regex;
    }

    public void setRegex(String regex) {
        this.regex = regex;
    }

    public String getReplacement() {
        return replacement;
    }

    public void setReplacement(String replacement) {
        this.replacement = replacement;
    }

}

