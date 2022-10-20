package com.hoshino.study.logback.sensitive;

/**
 * sensitiveProperty
 * @author huangyuehao
 */
public class LogbackSensitive {

    /**
     * sensitiveType(ky:键值脱敏, regex:正则脱敏)
     */
    private String type;

    /**
     * regex pattern
     */
    private String regex;

    /**
     * replace CharSequence
     */
    private String replacement;

    /**
     * sensitive key
     */
    private String key;

    /**
     * separate key value
     */
    private String split;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getRegex() {
        return regex;
    }

    public String getReplacement() {
        return replacement;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getSplit() {
        return split;
    }

    public void setSplit(String split) {
        this.split = split;
    }
}

