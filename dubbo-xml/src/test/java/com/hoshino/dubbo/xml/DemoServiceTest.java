package com.hoshino.dubbo.xml;

import com.hoshino.dubbo.xml.api.DemoService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * @author huangyuehao
 * @date 2023-02-02
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath*:dubbo-demo-consumer.xml")
public class DemoServiceTest {

    @Autowired
    private DemoService demoService;

    @Test
    public void testDubbo() {
        Assert.assertTrue(demoService.sayHello("dubbo").startsWith("Hello dubbo"));
    }
}
