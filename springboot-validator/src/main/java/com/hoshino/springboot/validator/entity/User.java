package com.hoshino.springboot.validator.entity;

import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * @author Akino
 * @date 2023-08-18
 */
@Data
public class User implements Serializable {

    private static final long serialVersionUID = -6566711009842675799L;

    @NotNull(message = "用户id不能为空")
    private Integer id;

    @Length(min = 6, max = 10, message = "长度不能小于六位且不能大于十位")
    private String username;

    @Email(message = "请输入正确的邮箱")
    private String email;
}
