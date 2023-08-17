package com.hoshino.springboot.schedule.job;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author huangyuehao
 * @date 2023-07-03
 */
@Component
@Slf4j
public class RefreshDataJob {

    private final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    /**
     * 根据 Cron 表达式执行任务。https://www.bejson.com/othertools/cron/
     */
    @Scheduled(cron = "0 30 12 * * ?")
    public void refreshData() {
        log.info(">>>>> 每天中午12:30定时任务，刷新数据开始......");
        handler();
        log.info(">>>>> 每天中午12:30定时任务，刷新数据完成......");
    }

    private void handler() {
        // do something
    }

    /**
     * fixedRate 执行时间间隔，固定每隔 5000 毫秒（5 秒）执行一次，无需等待上次任务完成。
     */
    @Scheduled(fixedRate = 5000)
    public void fixedRateTask() {
        log.info("Fixed rate task executed at " + dateFormat.format(new Date()));
    }


    /**
     * fixedDelay 执行时间间隔，在上一次任务完成后等待 5000 毫秒（5 秒）再执行。
     */
    @Scheduled(fixedDelay = 5000)
    public void fixedDelayTask() {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        log.info("Fixed delay task executed at " + dateFormat.format(new Date()));
    }

}
