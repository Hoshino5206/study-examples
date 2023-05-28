package com.hoshino.springboot.multisource.config;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceBuilder;
import com.hoshino.springboot.multisource.constant.DataSourceType;
import com.hoshino.springboot.multisource.datasource.DynamicDataSource;
import com.hoshino.springboot.multisource.properties.MybatisProperties;
import lombok.RequiredArgsConstructor;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.transaction.TransactionManagerCustomizers;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.util.*;

/**
 * 多数据源、动态数据源配置
 *
 * @author huangyuehao
 * @date 2023-04-23
 */
@Configuration
@RequiredArgsConstructor
@EnableTransactionManagement
public class MultiDataSourceConfig {

    private final Interceptor[] interceptors;

    /**
     * 主数据源
     */
    @Configuration
    @MapperScan(basePackages = "com.hoshino.springboot.multisource.dao.master", sqlSessionTemplateRef = "masterSqlSessionTemplate")
    @EnableConfigurationProperties({MybatisProperties.class})
    public class MasterDataSourceConfiguration {

        @Bean
        @ConfigurationProperties(prefix = "mybatis-config.master")
        public MybatisProperties masterMybatisProperties() {
            return new MybatisProperties();
        }

        @Bean
        @ConfigurationProperties(prefix = "spring.datasource.druid.master")
        public DruidDataSource masterDataSource() {
            return DruidDataSourceBuilder.create().build();
        }

        @Bean
        public SqlSessionFactory masterSqlSessionFactory(@Qualifier("masterDataSource") DruidDataSource dataSource,
                                                         @Qualifier("masterMybatisProperties") MybatisProperties mybatisProperties) throws Exception {
            return createSqlSessionFactory(dataSource, mybatisProperties);
        }

        @Bean
        public SqlSessionTemplate masterSqlSessionTemplate(@Qualifier("masterSqlSessionFactory") SqlSessionFactory sqlSessionFactory) {
            return new SqlSessionTemplate(sqlSessionFactory);
        }

        @Bean
        public DataSourceTransactionManager masterTransactionManager(@Qualifier("masterDataSource") DataSource dataSource, ObjectProvider<TransactionManagerCustomizers> transactionManagerCustomizers) {
            DataSourceTransactionManager transactionManager = new DataSourceTransactionManager(dataSource);
            transactionManagerCustomizers.ifAvailable((customizers) -> {
                customizers.customize(transactionManager);
            });
            return transactionManager;
        }
    }

    /**
     * 从数据源
     */
    @Configuration
    @MapperScan(basePackages = "com.hoshino.springboot.multisource.dao.slave", sqlSessionTemplateRef = "slaveSqlSessionTemplate")
    @EnableConfigurationProperties({MybatisProperties.class})
    public class SlaveDataSourceConfiguration {

        @Bean
        @ConfigurationProperties(prefix = "mybatis-config.slave")
        public MybatisProperties slaveMybatisProperties() {
            return new MybatisProperties();
        }

        @Bean
        @ConfigurationProperties(prefix = "spring.datasource.druid.slave")
        public DruidDataSource slaveDataSource() {
            return DruidDataSourceBuilder.create().build();
        }

        @Bean
        public SqlSessionFactory slaveSqlSessionFactory(@Qualifier("slaveDataSource") DruidDataSource dataSource,
                                                         @Qualifier("slaveMybatisProperties") MybatisProperties mybatisProperties) throws Exception {
            return createSqlSessionFactory(dataSource, mybatisProperties);
        }

        @Bean
        public SqlSessionTemplate slaveSqlSessionTemplate(@Qualifier("slaveSqlSessionFactory") SqlSessionFactory sqlSessionFactory) {
            return new SqlSessionTemplate(sqlSessionFactory);
        }

        @Bean
        public DataSourceTransactionManager slaveTransactionManager(@Qualifier("slaveDataSource") DataSource dataSource, ObjectProvider<TransactionManagerCustomizers> transactionManagerCustomizers) {
            DataSourceTransactionManager transactionManager = new DataSourceTransactionManager(dataSource);
            transactionManagerCustomizers.ifAvailable((customizers) -> {
                customizers.customize(transactionManager);
            });
            return transactionManager;
        }
    }

    /**
     * 动态数据源，共用一个sqlSessionFactory会话工厂
     */
    @Configuration
    @MapperScan("com.hoshino.springboot.multisource.dao.dynamic")
    @EnableConfigurationProperties({MybatisProperties.class})
    public class DynamicDataSourceConfiguration {

        @Bean
        @ConfigurationProperties(prefix = "mybatis-config.dynamic")
        public MybatisProperties dynamicMybatisProperties() {
            return new MybatisProperties();
        }

        @Bean
        @ConfigurationProperties("spring.datasource.druid.dynamic1")
        public DataSource dynamic1DataSource() {
            return DruidDataSourceBuilder.create().build();
        }

        @Bean
        @ConfigurationProperties("spring.datasource.druid.dynamic2")
        public DataSource dynamic2DataSource() {
            return DruidDataSourceBuilder.create().build();
        }

        @Bean
        @Primary
        public DynamicDataSource dynamicDataSource(@Qualifier("dynamic1DataSource") DataSource dynamic1DataSource,
                                                   @Qualifier("dynamic2DataSource") DataSource dynamic2DataSource) {
            Map<Object, Object> targetDataSources = new HashMap<>(2);
            targetDataSources.put(DataSourceType.MASTER, dynamic1DataSource);
            targetDataSources.put(DataSourceType.SLAVE, dynamic2DataSource);
            return new DynamicDataSource(dynamic1DataSource, targetDataSources);
        }

        @Bean
        public SqlSessionFactory dynamicSqlSessionFactory(@Qualifier("dynamicDataSource") DataSource dataSource,
                                                          @Qualifier("dynamicMybatisProperties") MybatisProperties mybatisProperties) throws Exception {
            return createSqlSessionFactory(dataSource, mybatisProperties);
        }

        @Bean
        public SqlSessionTemplate dynamicSqlSessionTemplate(@Qualifier("dynamicSqlSessionFactory") SqlSessionFactory sqlSessionFactory) {
            return new SqlSessionTemplate(sqlSessionFactory);
        }

        @Bean
        public DataSourceTransactionManager dynamicTransactionManager(@Qualifier("dynamicDataSource") DataSource dataSource, ObjectProvider<TransactionManagerCustomizers> transactionManagerCustomizers) {
            DataSourceTransactionManager transactionManager = new DataSourceTransactionManager(dataSource);
            transactionManagerCustomizers.ifAvailable((customizers) -> {
                customizers.customize(transactionManager);
            });
            return transactionManager;
        }
    }

    /**
     * 创建sqlSessionFactory会话工厂
     * @param dataSource 数据源
     * @param mybatisProperties mybatis配置
     * @return 会话工厂
     */
    private SqlSessionFactory createSqlSessionFactory(DataSource dataSource, MybatisProperties mybatisProperties) throws Exception {
        SqlSessionFactoryBean factory = new SqlSessionFactoryBean();
        factory.setDataSource(dataSource);
        factory.setPlugins(interceptors);

        // 设置全局配置
        org.apache.ibatis.session.Configuration configuration = new org.apache.ibatis.session.Configuration();
        // 开启自动驼峰命名规则
        configuration.setMapUnderscoreToCamelCase(true);
        factory.setConfiguration(configuration);

        List<Resource> resources = new ArrayList<>();
//        String[] mapperXmls = StringUtils.split(mybatisProperties.getMapperXml(), ",");
//        for (String mapperXml : mapperXmls) {
//            resources.addAll(Arrays.asList(new PathMatchingResourcePatternResolver().getResources(mapperXml)));
//        }
        String mapperXml = mybatisProperties.getMapperXml();
        resources.addAll(Arrays.asList(new PathMatchingResourcePatternResolver().getResources(mapperXml)));
        factory.setMapperLocations(resources.toArray(new Resource[resources.size()]));
        factory.setTypeAliasesPackage(mybatisProperties.getTypeAliasesPackage());
        Objects.requireNonNull(factory);

        return factory.getObject();
    }

}
