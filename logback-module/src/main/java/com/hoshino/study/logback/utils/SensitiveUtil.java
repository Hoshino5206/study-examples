package com.hoshino.study.logback.utils;

import ch.qos.logback.classic.spi.LoggingEvent;
import com.hoshino.study.logback.constant.SensitiveTypeConstant;
import com.hoshino.study.logback.sensitive.LogbackSensitive;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * logback日志脱敏工具
 * @author huangyuehao
 */
public class SensitiveUtil {

    private static final Logger LOGGER = LoggerFactory.getLogger(SensitiveUtil.class);

    public static String buildMsgSensitive(LoggingEvent event) {
        String formattedMessage = event.getFormattedMessage();
        String message = event.getMessage();
        try {
            Map<String, LogbackSensitive> resource = PropertiesUtil.getResource();
            Set<String> keys = resource.keySet();
            String sensitiveMsg = formattedMessage;
            // keys脱敏字段
            for (String key : keys) {
                boolean isFind = matcher(key, message).find();
                if (isFind) {
                    // value脱敏规则
                    LogbackSensitive logbackSensitive = resource.get(key);
                    String type = logbackSensitive.getType();
                    String replacement = logbackSensitive.getReplacement();
                    if (SensitiveTypeConstant.REGEX.equals(type)) {
                        String regex = logbackSensitive.getRegex();
                        sensitiveMsg = msgSensitive(regex, formattedMessage, replacement);
                    }
                    if (SensitiveTypeConstant.KV.equals(type)) {
                        String[] fields = logbackSensitive.getKey().split(",");
                        String[] splits = logbackSensitive.getSplit().split(",");
                        for (String filed : fields) {
                            for (String sp : splits) {
                                String regex = filed + sp + "(\\s*)(\\w*)";
                                if (matcher(regex, formattedMessage).find()){
                                    sensitiveMsg = msgSensitive(regex, sensitiveMsg, filed + sp + replacement);
                                }
                            }
                        }
                    }
                }
            }
            return sensitiveMsg;
        } catch (Exception e) {
            LOGGER.error("logback sensitive error:{}", e.getMessage());
        }
        return formattedMessage;
    }

    private static Pattern regexFormat(String regex) {
        return Pattern.compile(regex);
    }

    private static Matcher matcher(String regex, String msg) {
        return regexFormat(regex).matcher(msg);
    }

    private static String msgSensitive(String regex, String msg, String replacement) {
        return matcher(regex, msg).replaceAll(replacement);
    }

}
