package com.hoshino.spring.bean;

import org.springframework.beans.factory.InitializingBean;

/**
 * @author huangyuehao
 * @date 2023-02-08
 */
public class MyInitializingBean implements InitializingBean {

    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("[InitializingBean]invoke afterPropertiesSet() ");
    }

}
