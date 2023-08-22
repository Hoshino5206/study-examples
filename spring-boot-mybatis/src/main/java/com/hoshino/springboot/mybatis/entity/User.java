package com.hoshino.springboot.mybatis.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @author huangyuehao
 * @date 2023-06-28
 */
@Data
public class User implements Serializable {

    private static final long serialVersionUID = -6675417131553360839L;

    private Integer id;

    private String username;

    private String password;

    private String creator;

    private String updater;

    /**
     * 用于服务器返回Json数据时，对Date类型时间进行的格式化，实体类过多时显得真臃肿，建议使用WebMvcConfig配置configureMessageConverters
     * 详细配置可见springboot-config
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone="GMT+8")
    private Date createTime;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone="GMT+8")
    private Date updateTime;

    private Integer isDelete;

}
