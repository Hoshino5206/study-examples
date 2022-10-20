package com.hoshino.study.logback.appender;

import ch.qos.logback.classic.spi.LoggingEvent;
import ch.qos.logback.core.rolling.RollingFileAppender;
import com.hoshino.study.logback.utils.SensitiveUtil;

import java.lang.reflect.Field;

/**
 * 重写滚动文件Appender实现脱敏
 * @author huangyuehao
 */
public class OutputSensitiveAppender extends RollingFileAppender<LoggingEvent> {

    @Override
    protected void subAppend(LoggingEvent event) {
        String buildMsgSensitive = SensitiveUtil.buildMsgSensitive(event);
        try {
            Field message = event.getClass().getDeclaredField("message");
            Field formattedMessage = event.getClass().getDeclaredField("formattedMessage");
            message.setAccessible(true);
            message.set(event, buildMsgSensitive);
            formattedMessage.setAccessible(true);
            formattedMessage.set(event, buildMsgSensitive);
        } catch (Exception e) {
            e.printStackTrace();
        }
        super.subAppend(event);
    }

}
