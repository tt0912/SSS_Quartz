package com.qf.test;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.qf.service.RankService;
import org.quartz.Job;
import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
/**
 *  @author 田宇
 *  @date 2018/1/30 0030 20:41
 *  @description 测试对象交互
 */
public class RankJob implements Job{

    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {

        //1.jobDataMap用于传递对象
        JobDataMap jobDataMap = jobExecutionContext.getJobDetail().getJobDataMap();
        RankService rankService = (RankService)jobDataMap.get("rankService");
        System.out.println(rankService);
        try {
            System.out.println(new ObjectMapper().writeValueAsString(rankService.getByPage(1,3)));
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
