package com.hoshino.springboot.mybatisplus.entity;

import com.baomidou.mybatisplus.annotation.*;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @author huangyuehao
 * @date 2023-06-28
 */
@Data
@TableName("t_system_user")
public class User implements Serializable {

    private static final long serialVersionUID = -6675417131553360839L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @TableField(value = "username")
    private String username;

    @TableField(value = "password")
    private String password;

    @TableField(value = "creator", fill = FieldFill.INSERT)
    private String creator;

    @TableField(value = "updater", fill = FieldFill.INSERT_UPDATE)
    private String updater;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone="GMT+8")
    @TableField(value = "create_time", fill = FieldFill.INSERT)
    private Date createTime;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone="GMT+8")
    @TableField(value = "update_time", fill = FieldFill.INSERT_UPDATE)
    private Date updateTime;

//    @TableLogic(value = "0", delval = "1")
    @TableField(value = "is_delete")
    private Integer isDelete;

}
