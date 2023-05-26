package com.hoshino.springboot.autoconfigure;

import com.hoshino.springboot.autoconfigure.person.PersonAutoConfiguration;
import com.hoshino.springboot.autoconfigure.person.PersonConfiguration;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.springframework.boot.autoconfigure.AutoConfigurations;
import org.springframework.boot.test.context.runner.ApplicationContextRunner;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

/**
 * @author huangyuehao
 * @date 2023-05-25
 */
@RunWith(JUnit4.class)
public class PersonAutoConfigurationTest {

    private final ApplicationContextRunner contextRunner = new ApplicationContextRunner()
            .withConfiguration(AutoConfigurations.of(PersonAutoConfiguration.class))
            .withPropertyValues("com.hoshino.example.enable=true");

    @Test
    public void configurationTest() {
        contextRunner.run(context -> {
            PersonConfiguration configuration = context.getBean(PersonConfiguration.class);
            assertThat(configuration).isNotNull();
        });
    }

}
