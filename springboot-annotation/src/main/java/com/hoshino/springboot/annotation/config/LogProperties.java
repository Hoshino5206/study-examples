package com.hoshino.springboot.annotation.config;

import com.hoshino.springboot.annotation.anno.Log;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.mvc.method.RequestMappingInfo;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

import java.lang.annotation.Annotation;
import java.util.Arrays;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

/**
 * @author huangyuehao
 * @date 2023-04-06
 */
public class LogProperties implements ApplicationContextAware, InitializingBean {

    private ApplicationContext applicationContext;

    @Override
    public void setApplicationContext(ApplicationContext context) throws BeansException {
        this.applicationContext = context;
    }

    @Override
    public void afterPropertiesSet() {
        RequestMappingHandlerMapping mapping = applicationContext.getBean(RequestMappingHandlerMapping.class);
        Map<RequestMappingInfo, HandlerMethod> map = mapping.getHandlerMethods();

        map.keySet().forEach(info -> {
            HandlerMethod handlerMethod = map.get(info);
            Log log = AnnotationUtils.findAnnotation(handlerMethod.getMethod(), Log.class);

            if (log != null) {
                System.out.println("log.title() = " + log.title());
                System.out.println("log.isSaveRequestData() = " + log.isSaveRequestData());
                System.out.println("log.isSaveResponseData() = " + log.isSaveResponseData());
                System.out.println("log.includeParamNames() = " + Arrays.toString(log.includeParamNames()));
                System.out.println("log.excludeParamNames() = " + Arrays.toString(log.excludeParamNames()));
            }
        });
    }

}
