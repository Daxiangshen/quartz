package com.quartz.apiTigger.startTimeAndendTime;

import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;

import java.util.Date;

public class SecondScheduler {
    public static void main(String[] args) throws SchedulerException {
        JobDetail jobDetail = JobBuilder.newJob(HelloJob.class).withIdentity("Job").build();
        //开始时间  3秒钟之后(具体时间按实际业务编写)
        Date sData=new Date();
        sData.setTime(sData.getTime()+3000);
        //结束时间  6秒钟之后(具体时间按实际业务编写)
        Date eData=new Date();
        eData.setTime(sData.getTime()+6000);
        //创建一个Trigger实例，定义该job3秒之后执行，在6秒之后结束
        SimpleTrigger simpleTrigger= TriggerBuilder.newTrigger().withIdentity("Trigger")
                //设置开始时间
                .startAt(sData)
                //设置结束时间
                .endAt(eData)
                //每两秒打印一次
                .withSchedule(SimpleScheduleBuilder.simpleSchedule().withIntervalInSeconds(2).repeatForever()).build();
        //创建Scheduler实例
        StdSchedulerFactory stdSchedulerFactory = new StdSchedulerFactory();
        Scheduler scheduler = stdSchedulerFactory.getScheduler();
        scheduler.start();
        scheduler.scheduleJob(jobDetail,simpleTrigger);
    }
}
