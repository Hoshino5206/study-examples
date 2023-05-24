package com.hoshino.springboot.multisource.util;

import lombok.extern.slf4j.Slf4j;

/**
 * 动态数据源切换处理
 *
 * @author huangyuehao
 * @date 2023-04-23
 */
@Slf4j
public class DataSourceContextHolder {

    /**
     * ThreadLocal 用于提供线程局部变量，在多线程环境可以保证各个线程里的变量独立于其它线程里的变量。
     * 也就是说 ThreadLocal 可以为每个线程创建一个【单独的变量副本】，相当于线程的 private static 类型变量。
     */
    private static final ThreadLocal<String> CONTEXT_HOLDER = new ThreadLocal<>();

    private DataSourceContextHolder() {

    }

    /**
     * 设置数据源变量
     */
    public static void setDataSourceType(String dataSource)
    {
        log.info("切换数据源:{} ", dataSource);
        CONTEXT_HOLDER.set(dataSource);
    }

    /**
     * 获得数据源变量
     */
    public static String getDataSourceType()
    {
        return CONTEXT_HOLDER.get();
    }

    /**
     * 清空数据源变量
     */
    public static void clearDataSourceType()
    {
        CONTEXT_HOLDER.remove();
    }

}
