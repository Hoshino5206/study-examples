package com.hoshino.springboot.validator.config;

import com.hoshino.springboot.validator.common.ResponseResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;

/**
 * @author Akino
 * @date 2023-08-18
 */
@RestControllerAdvice
@Slf4j
public class MyRestControllerAdvice {

    /**
     * 校验错误拦截处理
     *
     * @param exception 错误信息集合
     * @return 错误信息
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseResult<String> validationBodyException(MethodArgumentNotValidException exception) {
        BindingResult result = exception.getBindingResult();
        if (result.hasErrors()) {
            List<ObjectError> errors = result.getAllErrors();
            errors.forEach(p -> {
                FieldError fieldError = (FieldError) p;
                log.warn("数据校验失败, object={}, field={}, errorMessage={}", fieldError.getObjectName(), fieldError.getField(), fieldError.getDefaultMessage());
            });
            //只返回第一个
            return ResponseResult.error(errors.get(0).getDefaultMessage());
        }
        //其他错误
        return ResponseResult.error(exception.getMessage());
    }
}
