package com.hoshino.springboot.annotation.util;

import com.hoshino.springboot.annotation.annotation.Logging;
import lombok.extern.slf4j.Slf4j;

import java.util.Arrays;

/**
 * @author huangyuehao
 * @date 2023-05-26
 */
@Slf4j
public class LogUtil {

    public static void logPrintOut(Logging logging) {
        if (logging != null) {
            log.info("log.title: {}", logging.title());
            log.info("log.isSaveRequestData: {}", logging.isSaveRequestData());
            log.info("log.isSaveResponseData: {}", logging.isSaveResponseData());
            log.info("log.includeParamNames: {}", Arrays.toString(logging.includeParamNames()));
            log.info("log.excludeParamNames: {}", Arrays.toString(logging.excludeParamNames()));
        }
    }

}
