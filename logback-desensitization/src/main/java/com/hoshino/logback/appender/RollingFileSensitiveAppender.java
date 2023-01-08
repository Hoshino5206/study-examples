package com.hoshino.logback.appender;

import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.core.rolling.RollingFileAppender;
import com.hoshino.logback.sensitive.LogbackConstants;
import com.hoshino.logback.sensitive.LoggingDesensitization;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.lang.reflect.Field;

/**
 * 重写滚动文件Appender实现脱敏.
 * @author huangyuehao
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class RollingFileSensitiveAppender extends RollingFileAppender<ILoggingEvent> {

    private LoggingDesensitization loggingDesensitization;

    @Override
    protected void subAppend(ILoggingEvent event) {
        String buildMsgSensitive = loggingDesensitization.buildMsgSensitive(event.getFormattedMessage());
        try {
            Field message = event.getClass().getDeclaredField(LogbackConstants.MESSAGE);
            Field formattedMessage = event.getClass().getDeclaredField(LogbackConstants.FORMATTED_MESSAGE);
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
