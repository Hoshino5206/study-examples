package com.hoshino.springboot.validator.common;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 统一封装结果集.
 *
 * @author Akino
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResponseResult<T> {

    private Integer code;

    private String msg;

    private T data;

    public ResponseResult(T data) {
        this.data = data;
    }

    public ResponseResult(String msg, T data) {
        this.msg = msg;
        this.data = data;
    }

    public static <T> ResponseResult<Void> success() {
        ResponseResult<Void> responseResult = new ResponseResult<>();
        responseResult.code = 200;
        responseResult.msg = "success";
        return responseResult;
    }

    public static <T> ResponseResult<T> success(T object) {
        ResponseResult<T> responseResult = new ResponseResult<>();
        responseResult.code = 200;
        responseResult.msg = "success";
        responseResult.data = object;
        return responseResult;
    }

    public static <T> ResponseResult<T> success(String msg) {
        ResponseResult<T> responseResult = new ResponseResult<>();
        responseResult.code = 200;
        responseResult.msg = msg;
        responseResult.data = null;
        return responseResult;
    }

    public static <T> ResponseResult<T> success(String msg, T object) {
        ResponseResult<T> responseResult = new ResponseResult<>();
        responseResult.code = 200;
        responseResult.msg = msg;
        responseResult.data = object;
        return responseResult;
    }

    public static <T> ResponseResult<T> error(String msg) {
        ResponseResult<T> responseResult = new ResponseResult<>();
        responseResult.code = 400;
        responseResult.msg = msg;
        responseResult.data = null;
        return responseResult;
    }
}
