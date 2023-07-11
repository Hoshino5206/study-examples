package com.hoshino.springboot.quartz.job;

import lombok.extern.slf4j.Slf4j;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import java.util.ArrayList;
import java.util.List;

/**
 * @author huangyuehao
 * @date 2023-07-11
 */
@Slf4j
public class RefreshDataJob implements Job {

    private final List<Integer> list = new ArrayList<>();

    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        list.clear();
        log.info("RefreshDataJob start ......");
        list.add(1);
        list.add(2);
        list.add(3);
        log.info("RefreshDataJob end ......");
    }

}
