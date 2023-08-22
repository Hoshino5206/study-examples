package com.hoshino.springboot.multisource.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author huangyuehao
 * @date 2023-05-24
 */
@Data
@ConfigurationProperties(prefix = "mybatis-config")
public class MybatisProperties {

    private String mapperXml;

    private String typeAliasesPackage;

}
