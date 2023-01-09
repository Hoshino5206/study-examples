package com.hoshino.springmvc.interceptor.exception;

/**
 * @author huangyuehao
 * @date 2023-01-06
 */
/**
 * 自定义的运行异常，在需要抛出运行时异常，可使用该异常.
 *
 * @author Akino
 */
public class MyRuntimeException extends RuntimeException {

    /**
     * 构造函数.
     */
    public MyRuntimeException() {
    }

    /**
     * 构造函数.
     *
     * @param cause 引发异常对象.
     */
    public MyRuntimeException(Throwable cause) {
        super(cause);
    }

    /**
     * 构造函数.
     *
     * @param message 错误信息.
     */
    public MyRuntimeException(String message) {
        super(message);
    }
}
