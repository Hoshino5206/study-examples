package com.hoshino.example.boot.properties;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

/**
 * @author huangyuehao
 * @date 2022-09-28
 */
@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Component
@EnableConfigurationProperties
@ConfigurationProperties(prefix = "com.xhqb.kael")
public class PersonProperties {

    private String field;

    private Map<String, String> map;

    private Map<String, Stu> mapStu;

    private List<Stu> list;

    @Data
    @ToString
    public static class Stu {
        private String sex;
        private Integer age;
    }
}
