package com.hoshino.logback.sensitive;

import lombok.Data;

/**
 * sensitiveProperty
 * @author huangyuehao
 */
@Data
public class LoggingSensitive {

    /**
     * sensitive key
     */
    private String key;

    /**
     * regex pattern
     */
    private String regex;

    /**
     * replace CharSequence
     */
    private String replacement;

    /**
     * separate key value
     */
    private String split;

}

