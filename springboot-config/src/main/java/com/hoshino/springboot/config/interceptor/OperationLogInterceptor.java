package com.hoshino.springboot.config.interceptor;

import com.hoshino.springboot.config.annotation.OperationLog;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;
import java.util.Arrays;

/**
 * 拦截器获取注解
 * @author huangyuehao
 * @date 2023-04-06
 */
@Slf4j
public class OperationLogInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if (handler instanceof HandlerMethod) {
            HandlerMethod handlerMethod = (HandlerMethod) handler;
            Method method = handlerMethod.getMethod();
            OperationLog operationLog = method.getAnnotation(OperationLog.class);

            log.info("log.title: {}", operationLog.title());
            log.info("log.isSaveRequestData: {}", operationLog.isSaveRequestData());
            log.info("log.isSaveResponseData: {}", operationLog.isSaveResponseData());
            log.info("log.includeParamNames: {}", Arrays.toString(operationLog.includeParamNames()));
            log.info("log.excludeParamNames: {}", Arrays.toString(operationLog.excludeParamNames()));
            return true;
        }
        return false;
    }


    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
            ModelAndView modelAndView) throws Exception {
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response,
            Object handler, Exception ex) throws Exception {
    }
}
