package com.hoshino.springboot.mongodb.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;

/**
 * @author huangyuehao
 * @date 2023-05-23
 */
@Data
@Document(value = "user_document")
public class User implements Serializable {

    private static final long serialVersionUID = -6330308212250799653L;

    @Id
    private String id;

    private String username;

    private Integer age;

    private Address address;

}
