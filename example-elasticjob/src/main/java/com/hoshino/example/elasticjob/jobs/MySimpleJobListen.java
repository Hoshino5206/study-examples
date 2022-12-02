package com.hoshino.example.elasticjob.jobs;

import lombok.extern.slf4j.Slf4j;
import org.apache.shardingsphere.elasticjob.infra.listener.ElasticJobListener;
import org.apache.shardingsphere.elasticjob.infra.listener.ShardingContexts;
import org.springframework.stereotype.Component;

/**
 * @author huangyuehao
 * @date 2022-10-19
 */
@Component
@Slf4j
public class MySimpleJobListen implements ElasticJobListener {

    @Override
    public void beforeJobExecuted(ShardingContexts shardingContexts) {
        log.info("beforeJobExecuted: {}", shardingContexts.toString() );
    }

    @Override
    public void afterJobExecuted(ShardingContexts shardingContexts) {
        log.info("afterJobExecuted: {}", shardingContexts.toString() );
    }

    @Override
    public String getType () {
        return null;
    }
}
