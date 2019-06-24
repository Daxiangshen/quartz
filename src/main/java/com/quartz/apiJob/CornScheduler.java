package com.quartz.apiJob;

import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;

public class CornScheduler {
    public static void main(String[] args) throws SchedulerException {
        //1.创建一个jobDetail的实例，将该实例与HelloJob Class绑定
        //定义name和group
        JobDetail jobDetail= JobBuilder.newJob(HelloJob.class).withIdentity("myJob","group1")
                //加入属性到jobDataMap
                .usingJobData("message","hello MyJob1")
                //加入属性到jobDataMap
                .usingJobData("FloatJobValue",8.88f).build();

        //2.创建一个Trigger触发器的实例，定义该job立即执行，并且每2秒执行一次，一直执行
        SimpleTrigger trigger= TriggerBuilder.newTrigger().withIdentity("myTrigger","group1")
                .startNow()
                .withSchedule(SimpleScheduleBuilder.simpleSchedule().withIntervalInSeconds(2).repeatForever()).build();

        //3.创建schedule实例
        StdSchedulerFactory factory=new StdSchedulerFactory();
        Scheduler scheduler=factory.getScheduler();
        scheduler.start();
        scheduler.scheduleJob(jobDetail,trigger);
    }
}
