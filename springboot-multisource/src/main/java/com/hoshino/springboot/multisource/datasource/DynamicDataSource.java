package com.hoshino.springboot.multisource.datasource;

import com.hoshino.springboot.multisource.util.DataSourceContextHolder;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

import javax.sql.DataSource;
import java.util.Map;

/**
 * 动态数据源路由策略
 *
 * @author huangyuehao
 * @date 2023-04-23
 */
public class DynamicDataSource extends AbstractRoutingDataSource {

    /**
     * 决定使用哪个数据源之前需要把多个数据源的信息以及默认数据源信息配置好
     *
     * @param defaultTargetDataSource 默认数据源
     * @param targetDataSources 目标数据源
     */
    public DynamicDataSource(DataSource defaultTargetDataSource, Map<Object, Object> targetDataSources) {
        super.setDefaultTargetDataSource(defaultTargetDataSource);
        super.setTargetDataSources(targetDataSources);
        super.afterPropertiesSet();
    }

    /**
     * 设置动态数据源路由策略
     *
     * @return
     */
    @Override
    protected Object determineCurrentLookupKey() {
        return DataSourceContextHolder.getDataSourceType();
    }

}
