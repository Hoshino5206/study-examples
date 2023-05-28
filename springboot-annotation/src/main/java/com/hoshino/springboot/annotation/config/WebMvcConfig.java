package com.hoshino.springboot.annotation.config;

import com.hoshino.springboot.annotation.interceptor.OperationLogInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author huangyuehao
 * @date 2023-04-06
 */
@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new OperationLogInterceptor())
                .addPathPatterns("/**")
                .excludePathPatterns("/system/admin");
    }
}
