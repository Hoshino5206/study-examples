package com.hoshino.springboot.quartz.service;

import org.springframework.stereotype.Service;

/**
 * @author huangyuehao
 * @date 2023-07-11
 */
@Service
public interface QuartzService {

    void addJob();

    void pauseJob();

    void resumeJob();

    void rescheduleJob();

    void deleteJob();
}
