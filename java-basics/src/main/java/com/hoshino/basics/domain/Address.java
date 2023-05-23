package com.hoshino.basics.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * fluent: 不写默认为false，当该值为 true 时，对应字段的 getter 方法前面就没有 get，setter 方法就不会有 set。
 * chain: 不写默认为false，当该值为 true 时，对应字段的 setter 方法调用后，会返回当前对象。
 * prefix: 该属性是一个字符串数组，当该数组有值时，表示忽略字段中对应的前缀，生成对应的 getter 和 setter 方法。
 * makeFinal: getter 方法和 setter 方法都被 final 修饰
 *
 * @author huangyuehao
 * @date 2023-05-22
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(fluent = true, chain = true, makeFinal = true, prefix = "xx")
public class Address {

    private String xxProvince;
    private String xxCity;
    private String xxCounty;

    public static void main(String[] args) {
        Address address = new Address("123", "456", "789");
        address = address.city("666");
        String city = address.city();
        System.out.println("city = " + city);
    }

}


