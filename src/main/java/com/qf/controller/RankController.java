package com.qf.controller;

import com.qf.common.JsonResult;
import com.qf.domain.Job;
import com.qf.domain.Rank;
import com.qf.service.QuartzService;
import com.qf.service.RankService;
import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class RankController {

    @Autowired
    private RankService rankService;

    @Autowired
    private QuartzService quartzService;

    //进入系统的时候就开始缓存MySQL数据库中数据
    @RequestMapping("index.do")
    public String quartz() throws Exception{

        //1.配置默认计划对象
        Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();
        //2.配置触发条件CronTrigger
        CronTrigger cronTrigger = TriggerBuilder.newTrigger().withIdentity("bark","ranks").withSchedule(
                CronScheduleBuilder.cronSchedule("0 23 0 * * ?")
        ).build();
        //3.对象交互
        JobDetail jobDetail = JobBuilder.newJob(Job.class).build();
        jobDetail.getJobDataMap().put("quartzService",quartzService);
        scheduler.scheduleJob(jobDetail,cronTrigger);
        scheduler.start();
        return "redirect:rankpage.do";
    }

    @RequestMapping("rankadd.do")
    @ResponseBody
    public JsonResult add(Rank rank){

            if(rankService.save(rank)){
            return JsonResult.setOK(null);
            }else {
            return JsonResult.setERROR(null);
            }
    }

    @RequestMapping("rankpage.do")
    @ResponseBody
    public JsonResult add(Integer pageNum,Integer pageSize){

        Page<Rank> page = rankService.getByPage(pageNum,pageSize);
        return JsonResult.setOK(page);
    }
}