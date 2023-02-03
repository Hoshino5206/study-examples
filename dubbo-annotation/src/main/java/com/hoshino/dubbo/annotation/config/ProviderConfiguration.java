package com.hoshino.dubbo.annotation.config;

import org.apache.dubbo.config.ProviderConfig;
import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

/**
 * @author huangyuehao
 * @date 2023-02-03
 */
@Configuration
@EnableDubbo(scanBasePackages = "com.hoshino.dubbo.annotation.impl")
@PropertySource("classpath:/dubbo-provider.properties")
public class ProviderConfiguration {

    @Bean
    public ProviderConfig providerConfig() {
        ProviderConfig providerConfig = new ProviderConfig();
        providerConfig.setTimeout(1000);
        return providerConfig;
    }

}
