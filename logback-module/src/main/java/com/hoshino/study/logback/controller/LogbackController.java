package com.hoshino.study.logback.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author huangyuehao
 * @date 2022-10-09
 */
@RestController
@Slf4j
public class LogbackController {

    @RequestMapping("/logback/sensitive")
    public void logbackSensitive() {
        // 密码
        log.info("password:{}，密码: {}", "123456", "4567899900011");
        // 身份证号码
        log.debug("idCard:{}，身份证号: {}", "360123202111111122", "440239230120313200");
        // 19位卡号
        log.warn("cardNo:{}, 卡号: {}", "6602934033997686009", "6602934033997686009");
        // 地址
        log.error("address:{}, 地址: {}", "浙江省杭州市西湖区某条路123号", "上海市浦东区北京东路1-10号");
        // 邮箱
        log.info("邮箱:{}, email: {}", "alice@google.com", "bob520@163.com");
        // 姓名
        log.info("userName:{}，name: {}，姓名: {}", "张三" , "黄老五", "小花钱包");
        // 11位手机号
        log.info("mobile:{}, 手机号: {}", "13511112222", "13511113333");
        // 固定电话（带区号-）
        log.info("tel:{}, 座机: {}", "0791-83376222", "021-88331234");
    }
}
