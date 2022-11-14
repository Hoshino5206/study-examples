package com.hoshino.example.boot.properties;

import lombok.Data;
import org.apache.commons.lang3.StringUtils;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author huangyuehao
 * @date 2022-11-08
 */
@Data
@ConfigurationProperties(prefix = "example.spring.boot")
public class ExampleProperties {

    private Boolean enable = false;

    private String location = StringUtils.EMPTY;

}
