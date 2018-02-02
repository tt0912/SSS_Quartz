package com.qf.domain;

import com.qf.service.QuartzService;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import java.text.SimpleDateFormat;
import java.util.Date;
/**
 *  @author 田宇
 *  @date 2018/1/31 0031 0:28
 *  @description
 */
public class Job implements org.quartz.Job{

    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {

        //1.得到service对象
        QuartzService quartzService = (QuartzService)jobExecutionContext.getJobDetail().getJobDataMap().get("quartzService");
        System.out.println("开启定时备份:"+new SimpleDateFormat("HH:mm:ss").format(new Date()));
        //2.开始作业
        quartzService.barkRank();
        System.out.println("定时备份结束:"+new SimpleDateFormat("HH:mm:ss").format(new Date()));
    }
}
