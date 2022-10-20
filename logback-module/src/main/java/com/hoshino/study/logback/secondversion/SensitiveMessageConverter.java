package com.hoshino.study.logback.secondversion;

import ch.qos.logback.classic.pattern.MessageConverter;
import ch.qos.logback.classic.spi.ILoggingEvent;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;
import java.util.Set;

/**
 * logback日志脱敏信息转换器
 * 占位符方式，需要在logback-spring.xml中添加<conversionRule conversionWord="m" converterClass="com.xxx.xxx.ConsoleMessageConverter" />
 * @author huangyuehao
 */
public class SensitiveMessageConverter extends MessageConverter {

    private static final Logger LOGGER = LoggerFactory.getLogger(SensitiveMessageConverter.class);

    @Override
    public String convert(ILoggingEvent event) {
        String formattedMessage = event.getFormattedMessage();
        String message = event.getMessage();
        Object[] args = event.getArgumentArray();
        return buildMsgSensitive(formattedMessage, message, args);
    }

    private String buildMsgSensitive(String formattedMessage, String message, Object[] args) {
        try {
            Map<String, LogbackSensitive> resource = PropertiesUtil.getResource();
            Set<String> keys = resource.keySet();
            String sensitiveMsg = formattedMessage;
            // keys脱敏字段
            for (String key : keys) {
                boolean isFind = PropertiesUtil.matcher(key, message).find();
                if (isFind) {
                    // value脱敏规则
                    LogbackSensitive logbackSensitive = resource.get(key);
                    String type = logbackSensitive.getType();
                    switch (type) {
                        case LogbackSensitiveType.FULL:
                            sensitiveMsg = buildFullSensitive(key, sensitiveMsg, args);
                            break;
                        case LogbackSensitiveType.RANGE:
                            String position = logbackSensitive.getPosition();
                            sensitiveMsg = buildRangeSensitive(key, position, sensitiveMsg, args);
                            break;
                        case LogbackSensitiveType.REGEX:
                            String regex = logbackSensitive.getRegex();
                            String replacement = logbackSensitive.getReplacement();
                            sensitiveMsg = buildRegexSensitive(regex, sensitiveMsg, replacement);
                            break;
                        default:
                            LOGGER.error("sensitive type is not support");
                    }
                }
            }
            return sensitiveMsg;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return formattedMessage;
    }

    private String buildFullSensitive(String key, String sensitiveMsg, Object[] args) {
        for (Object arg : args) {
            String regex = "(" + key + ")(=|:|: |：)(" + arg + ")(\\]|\\\"|'|，|,)";
            boolean isMatter = PropertiesUtil.matcher(regex, sensitiveMsg).find();
            if (isMatter) {
                int count = arg.toString().length();
                StringBuilder sb = new StringBuilder();
                while (count != 0) {
                    count--;
                    sb.append("*");
                }
                String replacement = "$1$2" + sb + "$4";
                return PropertiesUtil.msgSensitive(regex, sensitiveMsg, replacement);
            }
        }
        return sensitiveMsg;
    }

    private String buildRangeSensitive(String key, String position, String sensitiveMsg, Object[] args) throws Exception {
        String[] split = position.split(",");
        int indexFrom = Integer.parseInt(split[0]);
        int indexTo = Integer.parseInt(split[1]);
        if (indexFrom > indexTo) {
            throw new Exception("脱敏配置文件有误，请检查修改后重试");
        }
        for (Object arg : args) {
            String regex = "(" + key + ")(=|:|: |：)(" + arg + ")(\\]|\\\"|'|，|,)";
            boolean isMatter = PropertiesUtil.matcher(regex, sensitiveMsg).find();
            if (isMatter) {
                String left = StringUtils.left(arg.toString(), indexFrom - 1);
                String right = StringUtils.right(arg.toString(), arg.toString().length() - indexTo);
                int count = indexTo - indexFrom + 1;
                StringBuilder sb = new StringBuilder();
                while (count != 0) {
                    count--;
                    sb.append("*");
                }
                String replacement = "$1$2" + left + sb + right + "$4";
                return PropertiesUtil.msgSensitive(regex, sensitiveMsg, replacement);
            }
        }
        return sensitiveMsg;
    }

    private String buildRegexSensitive(String regex, String sensitiveMsg, String replacement) {
        return PropertiesUtil.msgSensitive(regex, sensitiveMsg, replacement);
    }

}
