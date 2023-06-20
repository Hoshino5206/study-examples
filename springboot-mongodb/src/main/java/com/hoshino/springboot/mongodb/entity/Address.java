package com.hoshino.springboot.mongodb.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author huangyuehao
 * @date 2023-06-20
 */
@Data
@AllArgsConstructor
public class Address {

    private String country;

    private String city;

    private String fullAddress;

}
