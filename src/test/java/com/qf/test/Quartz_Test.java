package com.qf.test;

import com.qf.service.RankService;
import com.qf.test.Hello;
import com.qf.test.JsJob;
import org.quartz.*;
import org.quartz.impl.DirectSchedulerFactory;
import org.quartz.impl.StdSchedulerFactory;
import org.quartz.impl.triggers.SimpleTriggerImpl;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.Calendar;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

/**
 *  @author 田宇
 *  @date 2018/1/30 0030 14:20
 *  @description
 */
public class Quartz_Test {

    public static void main(String[] args) throws Exception{

        //test01();
        //test02();
        //test04();
        transfer();
    }


    //1.定时任务初学
    public static void test01() throws Exception{

        //1.获取默认计划对象
        Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();
        /*DirectSchedulerFactory directSchedulerFactory = DirectSchedulerFactory.getInstance();
        Scheduler scheduler1 = directSchedulerFactory.getScheduler();//不支持配置文件*/
        //2.配置触发条件
        Trigger trigger = TriggerBuilder.newTrigger().startNow().withSchedule(
                SimpleScheduleBuilder.repeatSecondlyForever()
        ).build();
        //3.整合作业与条件
        scheduler.scheduleJob(JobBuilder.newJob(Hello.class).build(),trigger);
        scheduler.start();
    }


    //2.java自带的定时任务
    public static void test02(){

        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                System.out.println("醒醒");
            }
        },0,2000);
    }



    //3.使用SimpleTrigger触发条件(用于:某个时间开始固定间隔至结束时间)
    public static void test03() throws Exception{

        //0.使用兰姆达表达式创建作业
        Job job = (JobExecutionContext jx)->{
            System.out.println("开始作业");
        };

        //1.获取默认计划对象
        Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();//支持配置文件

        //2.构建触发条件
        SimpleTriggerImpl simpleTrigger = new SimpleTriggerImpl();
        //2.1 配置重复次数
        simpleTrigger.setRepeatCount(6);
        //2.2 配置开始时间
        simpleTrigger.setStartTime(new Date());
        //2.3 配置结束时间
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.MINUTE,3);//3分钟后
        simpleTrigger.setEndTime(calendar.getTime());
        simpleTrigger.setRepeatInterval(5);//时间间隔

        //3.配置作业详情
        JobDetail jobDetail = JobBuilder.newJob(job.getClass()).build();

        //4.整合计时任务
        scheduler.scheduleJob(jobDetail,simpleTrigger);
        scheduler.start();
    }


    //Cron表达式写法:可以固定触发的时间
    /*
         秒、
         分钟、
         时、
         日(4L:这个月的倒数第4天;W:匹配最近的工作日;LW:当月最后一个工作日;5C:5号以后的第一天)、
         月、
         星期(L:星期6;6L:这个月最后的星期5;)、
         年(可选)
     */
    public static void test04() throws Exception{

        //1.配置默认计划对象
        Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();
        //2.配置具体的触发条件
        /*
         * withIdentity("","")给一个固定标识
         * withSchedule()去触发哪个条件
         */
        CronTrigger trigger = TriggerBuilder.newTrigger().withIdentity("cron1","group1").withSchedule(
                CronScheduleBuilder.cronSchedule("0 33 20 * * ?")).build();
        //3.整合计划详情与触发条件
        scheduler.scheduleJob(JobBuilder.newJob(JsJob.class).build(),trigger);
        scheduler.start();//开启
    }



    //传输对象
    public static void transfer() throws SchedulerException {

        ApplicationContext context = new ClassPathXmlApplicationContext("classpath:spring-config.xml");
        RankService rankService = context.getBean(RankService.class);
        System.out.println("rankService:"+rankService);

        //1.配置默认计划对象
        Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();

        //2.传递作业详情
        JobDetail jobDetail = JobBuilder.newJob(RankJob.class).build();
        JobDataMap jobDataMap = jobDetail.getJobDataMap();
        jobDataMap.put("rankService",rankService);

        //3.配置触发条件
        CronTrigger cronTrigger = TriggerBuilder.newTrigger()
                .withSchedule(CronScheduleBuilder.cronSchedule("50 04 21 * * ?")).build();

        //4.整合触发条件与作业详情
        scheduler.scheduleJob(jobDetail,cronTrigger);
        scheduler.start();
    }
}