package com.hoshino.springboot.quartz.service.impl;

import com.hoshino.springboot.quartz.job.RefreshDataJob;
import com.hoshino.springboot.quartz.service.QuartzService;
import lombok.extern.slf4j.Slf4j;
import org.quartz.*;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author huangyuehao
 * @date 2023-07-11
 */
@Service
@Slf4j
public class QuartzServiceImpl implements QuartzService {

    private final String jobName = "MyJob";
    private final String jobGroup = "jobs_group";
    private final String triggerName = "MyTrigger";
    private final String triggerGroup = "trigger_group";
    private final String cron = "0 0/5 * * * ?";

    @Resource
    private Scheduler scheduler;

    /**
     * https://juejin.cn/post/7216679822097252411
     * 新增定时任务
     */
    @Override
    public void addJob() {
        try {
            // 构建JobDetail
            JobDetail jobDetail = JobBuilder.newJob(RefreshDataJob.class)
                    .withIdentity(jobName, jobGroup)
                    .build();
            // 按新的cronExpression表达式构建一个新的trigger
            CronTrigger trigger = TriggerBuilder.newTrigger()
                    .withIdentity(triggerName, triggerGroup)
                    .startNow()
                    .withSchedule(CronScheduleBuilder.cronSchedule(cron))
                    .build();

            // 启动调度器
            scheduler.start();
            scheduler.scheduleJob(jobDetail, trigger);
        } catch (Exception e) {
            log.info("创建定时任务失败" + e);
        }
    }

    @Override
    public void pauseJob() {
        try {
            scheduler.pauseJob(JobKey.jobKey(this.jobName, this.jobGroup));
        } catch (SchedulerException e) {
            log.info("暂停定时任务失败" + e);
        }
    }

    @Override
    public void resumeJob() {
        try {
            scheduler.resumeJob(JobKey.jobKey(this.jobName, this.jobGroup));
        } catch (SchedulerException e) {
            log.info("恢复定时任务失败" + e);
        }
    }

    @Override
    public void rescheduleJob() {
        try {
            TriggerKey triggerKey = TriggerKey.triggerKey(this.jobName, this.jobGroup);
            // 表达式调度构建器
            CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule(cron);
            CronTrigger trigger = (CronTrigger) scheduler.getTrigger(triggerKey);

            // 按新的cronExpression表达式重新构建trigger
            trigger = trigger.getTriggerBuilder().withIdentity(triggerKey).withSchedule(scheduleBuilder).build();

            // 按新的trigger重新设置job执行，重启触发器
            scheduler.rescheduleJob(triggerKey, trigger);
        } catch (SchedulerException e) {
            log.info("重新调度定时任务失败" + e);
        }
    }

    @Override
    public void deleteJob() {
        try {
            scheduler.pauseTrigger(TriggerKey.triggerKey(this.jobName, this.jobGroup));
            scheduler.unscheduleJob(TriggerKey.triggerKey(this.jobName, this.jobGroup));
            scheduler.deleteJob(JobKey.jobKey(this.jobName, this.jobGroup));
        } catch (SchedulerException e) {
            log.info("删除定时任务失败" + e);
        }
    }
}
