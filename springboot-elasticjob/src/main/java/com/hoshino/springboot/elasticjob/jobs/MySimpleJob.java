package com.hoshino.springboot.elasticjob.jobs;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.apache.shardingsphere.elasticjob.api.ShardingContext;
import org.apache.shardingsphere.elasticjob.simple.job.SimpleJob;
import org.springframework.stereotype.Component;

/**
 * @author huangyuehao
 * @date 2022-10-19
 */
@Component
@Slf4j
public class MySimpleJob implements SimpleJob {

    @SneakyThrows
    @Override
    public void execute (ShardingContext shardingContext) {
        try {
            switch (shardingContext.getShardingItem()) {
                case 0:
                    Thread.sleep(2000);
                    log.info("sharding item 0: {}",  System.currentTimeMillis());
                    break;
                case 1:
                    Thread.sleep(3000);
                    log.info("sharding item 1: {}",  System.currentTimeMillis());
                    break;
                case 2:
                    Thread.sleep(4000);
                    log.info("sharding item 2: {}",  System.currentTimeMillis());
                    break;
                default:
                    log.info("default: {}",  shardingContext.getShardingItem());
            }
        } catch (Exception e) {
            log.error(e.getMessage());
        }
    }
}
