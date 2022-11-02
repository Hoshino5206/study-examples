package com.hoshino.example.spring.test;

import com.hoshino.example.spring.test.configuration.ConditionalOnClassConfiguration;
import org.junit.Test;
import org.springframework.boot.test.context.runner.ApplicationContextRunner;

/**
 * @author huangyuehao
 * @date 2022-11-02
 */
public class ConditionalTest {

    private final ApplicationContextRunner contextRunner = new ApplicationContextRunner();

    @Test
    public void OnClassConditionalTest() {
        this.contextRunner.withUserConfiguration(ConditionalOnClassConfiguration.class)
                .run(context -> {
                    Object create = context.getBean("create");
//                    System.out.println(create);
                });
        }
    }

    public void

