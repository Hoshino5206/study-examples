package com.hoshino.spring.aware;

import org.springframework.beans.factory.BeanClassLoaderAware;

/**
 * @author huangyuehao
 * @date 2023-02-08
 */
public class MyBeanClassLoader implements BeanClassLoaderAware {

    private ClassLoader classLoader;

    @Override
    public void setBeanClassLoader(ClassLoader classLoader) {
        this.classLoader = classLoader;
    }

}
