package com.hoshino.spring.aware;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.Map;

/**
 * @author huangyuehao
 * @date 2023-02-08
 */
@Component
public class MyApplicationContext implements ApplicationContextAware {

    private static ApplicationContext applicationContext;

    /**
     * Spring 启动的过程中会自动调用，并将应用上下文对象赋值进来。
     *
     * @param applicationContext 应用上下文对象，可通过该对象查找Spring中已经加载的Bean。
     */
    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        MyApplicationContext.applicationContext = applicationContext;
    }

    /**
     * 获取应用上下文对象。
     *
     * @return 应用上下文。
     */
    public static ApplicationContext getApplicationContext() {
        assertApplicationContext();
        return applicationContext;
    }

    /**
     * 根据BeanName，获取Bean对象。
     *
     * @param beanName Bean名称。
     * @param <T> 返回的Bean类型。
     * @return Bean对象。
     */
    @SuppressWarnings("unchecked")
    public static <T> T getBean(String beanName) {
        assertApplicationContext();
        return (T) applicationContext.getBean(beanName);
    }

    /**
     * 根据Bean的ClassType，获取Bean对象。
     *
     * @param beanType Bean的Class类型。
     * @param <T> 返回的Bean类型。
     * @return Bean对象。
     */
    public static <T> T getBean(Class<T> beanType) {
        assertApplicationContext();
        return applicationContext.getBean(beanType);
    }

    /**
     * 根据Bean的ClassType，获取Bean对象列表。
     *
     * @param beanType Bean的Class类型。
     * @param <T>      返回的Bean类型。
     * @return Bean对象列表。
     */
    public static <T> Collection<T> getBeanListOfType(Class<T> beanType) {
        assertApplicationContext();
        Map<String, T> beanMap = applicationContext.getBeansOfType(beanType);
        return beanMap == null ? null : beanMap.values();
    }

    private static void assertApplicationContext() {
        if (MyApplicationContext.applicationContext == null) {
            throw new RuntimeException("applicaitonContext属性为null, 请检查是否注入了ApplicationContext!");
        }
    }

}
