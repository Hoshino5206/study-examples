package com.hoshino.spring.bean;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;

/**
 * 1.执行 BeanPostProcessor 的前置处理方法 postProcessBeforeInitialization()，对 Bean 进行一些自定义的前置处理.
 * 2.判断Bean是否实现了 InitializingBean 接口，如果实现了将会执行 afterPropertiesSet() 初始化方法.
 * 3.执行用户自定义的初始化方法，如 init-method() 方法、@PostConstruct注解等.
 * 4.执行 BeanPostProcessor 的后置处理方法 postProcessAfterInitialization()
 * @author huangyuehao
 * @date 2023-02-08
 */
public class MyBeanPostProcessor implements BeanPostProcessor {

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        return BeanPostProcessor.super.postProcessBeforeInitialization(bean, beanName);
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        return BeanPostProcessor.super.postProcessAfterInitialization(bean, beanName);
    }

}
