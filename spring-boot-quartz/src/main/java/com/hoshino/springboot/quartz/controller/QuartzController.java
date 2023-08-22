package com.hoshino.springboot.quartz.controller;

import com.hoshino.springboot.quartz.service.QuartzService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author huangyuehao
 * @date 2023-07-11
 */
@RestController
@RequestMapping("/quartz/admin")
public class QuartzController {

    @Resource
    private QuartzService quartzService;

    @RequestMapping("/add")
    public void addJob() {
        quartzService.addJob();
    }

    @RequestMapping("/pause")
    public void pauseJob() {
        quartzService.pauseJob();
    }

    @RequestMapping("/resume")
    public void resumeJob() {
        quartzService.resumeJob();
    }

    @RequestMapping("/reschedule")
    public void rescheduleJob() {
        quartzService.rescheduleJob();
    }

    @RequestMapping("/delete")
    public void deleteJob() {
        quartzService.deleteJob();
    }

}
