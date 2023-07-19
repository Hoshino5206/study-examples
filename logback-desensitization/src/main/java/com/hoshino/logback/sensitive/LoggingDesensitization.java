package com.hoshino.logback.sensitive;

import lombok.Data;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.StopWatch;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * logback logging message desensitization core
 * @author huangyuehao
 */
@Data
public class LoggingDesensitization {

    private static final Logger LOGGER = LoggerFactory.getLogger(LoggingDesensitization.class);

    private final Map<String, Map<String, LoggingSensitive>> rules;

    public LoggingDesensitization(Map<String, Map<String, LoggingSensitive>> rules) {
        this.rules = rules;
    }

    /**
     * 构建信息脱敏.
     * @param formattedMessage 格式前信息
     * @return
     */
    public String buildMsg(String formattedMessage) {
        String sensitiveMsg = formattedMessage;
        if (rules == null || rules.isEmpty()) {
            return formattedMessage;
        }
        StopWatch sw = new StopWatch();
        sw.start();
        try {
            if (rules.containsKey(LogbackConstants.REGEX)) {
                Map<String, LoggingSensitive> map = rules.get(LogbackConstants.REGEX);
                Set<String> keys = map.keySet();
                for (String key : keys) {
                    LoggingSensitive loggingSensitive = map.get(key);
                    String regex = loggingSensitive.getRegex();
                    String replacement = loggingSensitive.getReplacement();
                    if (StringUtils.isAnyBlank(regex, replacement)) {
                        continue;
                    }
                    if (!matcher(regex, sensitiveMsg).find()) {
                        continue;
                    }
                    sensitiveMsg = msgSensitive(regex, sensitiveMsg, replacement);
                }
            }

            if (rules.containsKey(LogbackConstants.KV)) {
                Map<String, LoggingSensitive> map = rules.get(LogbackConstants.KV);
                Set<String> keys = map.keySet();
                for (String key : keys) {
                    LoggingSensitive loggingSensitive = map.get(key);
                    String keyStr = loggingSensitive.getKey();
                    String split = loggingSensitive.getSplit();
                    String replacement = loggingSensitive.getReplacement();
                    if (StringUtils.isAnyBlank(keyStr, split, replacement)) {
                        continue;
                    }
                    if (!matcher(keyStr, sensitiveMsg).find()) {
                        continue;
                    }

                    String[] splits = split.split(",");
                    for (String sp : splits) {
                        StringBuilder regex = new StringBuilder();
                        regex.append(keyStr).append(sp).append("([^,|，])*");
                        if (!matcher(regex.toString(), sensitiveMsg).find()) {
                            continue;
                        }
                        StringBuilder sb = new StringBuilder();
                        replacement = sb.append(key).append(sp).append(replacement).toString();
                        sensitiveMsg = msgSensitive(regex.toString(), sensitiveMsg, replacement);
                        break;
                    }
                }
            }
        } catch (Exception e) {
            LOGGER.error("logging desensitization is failure, check out the sensitive field of application properties");
        } finally {
            LOGGER.info("Started logging desensitization in {} micro seconds", sw.getTime(TimeUnit.MICROSECONDS));
        }
        return sensitiveMsg;
    }

    private Pattern regexFormat(String regex) {
        return Pattern.compile(regex, Pattern.CASE_INSENSITIVE);
    }

    private Matcher matcher(String regex, String msg) {
        return regexFormat(regex).matcher(msg);
    }

    private String msgSensitive(String regex, String msg, String replacement) {
        return matcher(regex, msg).replaceAll(replacement);
    }

}
