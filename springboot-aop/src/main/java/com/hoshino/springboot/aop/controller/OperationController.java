package com.hoshino.springboot.aop.controller;

import com.hoshino.springboot.aop.annotation.OperationLog;
import com.hoshino.springboot.aop.constants.OperationLogType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author huangyuehao
 * @date 2023-05-26
 */
@RestController
@RequestMapping("/aop/admin")
public class OperationController {

    @RequestMapping("login")
    @OperationLog(description = "登陆", type = OperationLogType.LOGIN)
    public String login() {
        return "login successfully";
    }

}
