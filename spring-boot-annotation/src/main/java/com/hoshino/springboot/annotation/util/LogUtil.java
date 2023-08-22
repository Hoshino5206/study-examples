package com.hoshino.springboot.annotation.util;

import com.hoshino.springboot.annotation.annotation.OperationLog;
import lombok.extern.slf4j.Slf4j;

import java.util.Arrays;

/**
 * @author huangyuehao
 * @date 2023-05-26
 */
@Slf4j
public class LogUtil {

    public static void logPrintOut(OperationLog operationLog) {
        if (operationLog != null) {
            log.info("log.title: {}", operationLog.title());
            log.info("log.isSaveRequestData: {}", operationLog.isSaveRequestData());
            log.info("log.isSaveResponseData: {}", operationLog.isSaveResponseData());
            log.info("log.includeParamNames: {}", Arrays.toString(operationLog.includeParamNames()));
            log.info("log.excludeParamNames: {}", Arrays.toString(operationLog.excludeParamNames()));
        }
    }

    private LogUtil(){

    }

}
