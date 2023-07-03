package com.hoshino.springboot.async.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.ThreadPoolExecutor;

/**
 * @author huangyuehao
 * @date 2023-05-15
 */
@Configuration
@EnableAsync
public class AsyncConfig {

    /**
     * 核心线程数
     * 默认的核心线程数为1
     */
    private static final int CORE_POOL_SIZE = 5;
    /**
     * 最大线程数
     * 默认的最大线程数是Integer.MAX_VALUE 即2的31次方-1
     */
    private static final int MAX_POOL_SIZE = 50;
    /**
     * 缓冲队列数
     * 默认的缓冲队列数是Integer.MAX_VALUE 即2的31次方-1
     */
    private static final int QUEUE_CAPACITY = 100;

    /**
     * 允许线程空闲时间
     * 默认的线程空闲时间为60秒
     */
    private static final int KEEP_ALIVE_SECONDS = 30;

    /**
     * allowCoreThreadTimeOut为true则线程池数量最后销毁到0个
     * allowCoreThreadTimeOut为false
     * 销毁机制：超过核心线程数时，而且（超过最大值或者timeout过），就会销毁。
     * 默认是false
     */
    private static final Boolean ALLOW_CORE_THREAD_TIMEOUT = false;

    /**
     * 线程池前缀名
     */
    private static final String THREAD_NAME_PREFIX = "TaskExecutor-";

    /**
     * Auto-configuration for TaskExecutor.
     *
     * @return ThreadPoolTaskExecutor Bean
     */
    @Bean("taskExecutor")
    public ThreadPoolTaskExecutor threadPoolTaskExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(CORE_POOL_SIZE);
        executor.setMaxPoolSize(MAX_POOL_SIZE);
        executor.setQueueCapacity(QUEUE_CAPACITY);
        executor.setKeepAliveSeconds(KEEP_ALIVE_SECONDS);
        executor.setAllowCoreThreadTimeOut(ALLOW_CORE_THREAD_TIMEOUT);
        executor.setThreadNamePrefix(THREAD_NAME_PREFIX);
        // 线程池对拒绝任务的处理策略
        executor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
        // 实现AsyncConfigurer接口需要调用initialize()线程初始化方法，如果使用Spring Bean管理方式则不需要。
        // 因为ThreadPoolTaskExecutor extend ExecutorConfigurationSupport implements InitializingBean，afterPropertiesSet()方法中实现了初始化
//        executor.initialize();
        return executor;
    }

}
