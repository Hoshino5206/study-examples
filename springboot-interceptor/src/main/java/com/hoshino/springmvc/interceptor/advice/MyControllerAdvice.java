package com.hoshino.springmvc.interceptor.advice;

import com.hoshino.springmvc.interceptor.common.ResponseResult;
import com.hoshino.springmvc.interceptor.exception.MyRuntimeException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;

/**
 * @author huangyuehao
 * @date 2023-01-06
 */
@RestControllerAdvice
@Slf4j
public class MyControllerAdvice {

    /**
     * 自定义运行时异常.
     */
    @ExceptionHandler(MyRuntimeException.class)
    public ResponseResult<String> exceptionHandler(MyRuntimeException e, HttpServletRequest request) {
        log.error("Custom runtime exception from URL [{}], Exception: ", request.getRequestURI(), e);
        return ResponseResult.error(e.getMessage());
    }

    /**
     * 运算条件异常.
     */
    @ExceptionHandler(ArithmeticException.class)
    public ResponseResult<String> exceptionHandler(ArithmeticException e, HttpServletRequest request) {
        log.error("ArithmeticException exception from URL [{}], Exception: ", request.getRequestURI(), e);
        return ResponseResult.error(e.getMessage());
    }

    /**
     * 参数校验异常.
     */
    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseResult<String> exceptionHandler(IllegalArgumentException e, HttpServletRequest request) {
        log.error("IllegalArgumentException exception from URL [{}], Exception: ", request.getRequestURI(), e);
        return ResponseResult.error(e.getMessage());
    }

}
