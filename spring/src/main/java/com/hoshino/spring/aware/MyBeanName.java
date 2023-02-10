package com.hoshino.spring.aware;

import org.springframework.beans.factory.BeanNameAware;

/**
 * @author huangyuehao
 * @date 2023-02-08
 */
public class MyBeanName implements BeanNameAware {

    private String beanName;

    @Override
    public void setBeanName(String name) {
        this.beanName = name;
    }

}
