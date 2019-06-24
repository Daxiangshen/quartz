package com.quartz.easy;

import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;

public class HelloScheduler {
    public static void main(String[] args) throws SchedulerException {
        //创建一个jobDetail的实例，将该实例与HellJob Class绑定
        JobDetail jobDetail= JobBuilder.newJob(HelloJob.class).withIdentity("myJob").build();
        //创建一个Trigger触发器的实例，定义该Job立即执行，并且每两秒执行一次，一直执行
        SimpleTrigger trigger= TriggerBuilder.newTrigger().withIdentity("myTrigger").startNow().withSchedule(SimpleScheduleBuilder.simpleSchedule().withIntervalInSeconds(2).repeatForever()).build();
        //创建工厂
        StdSchedulerFactory factory=new StdSchedulerFactory();
        //获取实例
        Scheduler scheduler=factory.getScheduler();
        //开启
        scheduler.start();
        scheduler.scheduleJob(jobDetail,trigger);
    }
}
