package com.hoshino.example.propertySource;

import com.hoshino.example.propertySource.configure.PersonConfiguration;
import com.hoshino.example.propertySource.configure.PersonProperties;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

/**
 * @author huangyuehao
 * @date 2022-11-29
 */
@SpringBootApplication
@Slf4j
public class Application {
    public static void main(String[] args) {
        ConfigurableApplicationContext run = SpringApplication.run(Application.class, args);
        PersonConfiguration bean = run.getBean(PersonConfiguration.class);
        PersonProperties properties = bean.getProperties();
        log.info("id:{} ", properties.getId());
        log.info("name:{} ", properties.getName());
        log.info("age:{} ", properties.getAge());
        log.info("birthday:{} ", properties.getBirthday());
        log.info("address:{} ", properties.getAddress());
        log.info("list:{} ", properties.getList());
        log.info("map:{} ", properties.getMap());
        log.info("ListAddress:{} ", properties.getListAdd());
        log.info("MapAddress:{} ", properties.getAddressMap());
    }
}
