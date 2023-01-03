package com.hoshino.basics.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author huangyuehao
 * @date 2022-10-21
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserFather {
    private String idCard;
    public String account;
}
