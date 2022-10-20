package com.hoshino.study.logback.controller;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author huangyuehao
 * @date 2022-10-09
 */
@RestController
@Slf4j
public class LogbackController {

    private static final Logger logger = LoggerFactory.getLogger(LogbackController.class);

    @RequestMapping("/logback/sensitive")
    public void logbackSensitive() {
        // 密码
        logger.info("password:{}，passWord: {}", "1234567890", "4567899900011");
        // 11位手机号
        logger.info("phone:{}, 手机号: {}", "13511112222", "13511113333");
        // 身份证号码
        logger.info("idCard:{}，身份证号: {}", "36012320219876123X", "440239230120313200");
        // 19位卡号
        logger.info("cardNo:{}, 卡号: {}", "6602934033997686009", "6602934033997686009");
        // 邮箱
        logger.info("邮箱:{}, email: {}", "alice@google.com", "bob520@163.com");
    }
}
