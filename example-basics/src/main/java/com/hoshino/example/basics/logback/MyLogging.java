package com.hoshino.example.basics.logback;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author huangyuehao
 * @date 2022-11-23
 */
public class MyLogging {

    private static final Logger LOGGER = LoggerFactory.getLogger(MyLogging.class);

    public static void main (String[] args) {
        LOGGER.info("test logger info");
    }
}
