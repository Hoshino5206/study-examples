package com.hoshino.springboot.interceptor.interceptor;

import com.hoshino.springboot.interceptor.common.ResponseResult;
import com.hoshino.springboot.interceptor.constant.HeaderConstant;
import com.hoshino.springboot.interceptor.util.InterceptorUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author huangyuehao
 * @date 2023-01-06
 */
@Component
@Slf4j
public class AuthenticationInterceptor implements HandlerInterceptor {

    private static final String TOKEN = "token";

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String authorization = request.getHeader(HeaderConstant.AUTHORIZATION);

        if (StringUtils.isEmpty(authorization)) {
            String errorMessage = "Invalid authorization, request header [Authorization] is empty";
            InterceptorUtils.outputResponseMessage(response, ResponseResult.error(errorMessage));
            log.error(errorMessage);
            return false;
        }
        if (!TOKEN.equals(authorization)) {
            String errorMessage = "Invalid authorization, request header [Authorization] is incorrect";
            InterceptorUtils.outputResponseMessage(response, ResponseResult.error(errorMessage));
            log.error(errorMessage);
            return false;
        }

        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
                           ModelAndView modelAndView) throws Exception {
        //空注解，否则sonar空代码块检查
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler,
                                Exception ex) throws Exception {
        //空注解，否则sonar空代码块检查
    }
}
