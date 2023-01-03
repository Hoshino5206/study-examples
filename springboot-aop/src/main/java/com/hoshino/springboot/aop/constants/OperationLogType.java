package com.hoshino.springboot.aop.constants;

/**
 * @author huangyuehao
 * @date 2022-12-14
 */
public class OperationLogType {

//    public static final String CREATE = "create";
//
//    public static final String UPDATE = "update";
//
//    public static final String DELETE = "delete";
//
//    public static final String QUERY = "query";
//
//    public static final String LIST = "list";

    /**
     * 其他。
     */
    public static final int OTHER = -1;
    /**
     * 登录。
     */
    public static final int LOGIN = 0;
    /**
     * 登出。
     */
    public static final int LOGOUT = 5;
    /**
     * 新增。
     */
    public static final int ADD = 10;
    /**
     * 修改。
     */
    public static final int UPDATE = 15;
    /**
     * 删除。
     */
    public static final int DELETE = 20;

}
