package com.hoshino.springboot.annotation.interceptor;

import com.hoshino.springboot.annotation.anno.Log;
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
public class LogInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if (handler instanceof HandlerMethod) {
            HandlerMethod handlerMethod = (HandlerMethod) handler;
            Method method = handlerMethod.getMethod();
            Log log = method.getAnnotation(Log.class);

            if (log != null) {
                System.out.println("log.title() = " + log.title());
                System.out.println("log.isSaveRequestData() = " + log.isSaveRequestData());
                System.out.println("log.isSaveResponseData() = " + log.isSaveResponseData());
                System.out.println("log.includeParamNames() = " + Arrays.toString(log.includeParamNames()));
                System.out.println("log.excludeParamNames() = " + Arrays.toString(log.excludeParamNames()));
            }
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
