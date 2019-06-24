package com.quartz.apiTigger.triggerImpl.dailyTimeIntervalTrigger;

import com.quartz.apiTigger.triggerImpl.calendarIntervalTrigger.HelloJob;
import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;

import java.text.SimpleDateFormat;
import java.util.Date;

import static org.quartz.DateBuilder.*;

public class DailyTimeIntervalSchedule {
    public static void main(String[] args) throws SchedulerException {
        SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        JobDetail jobDetail= JobBuilder.newJob(HelloJob.class).withIdentity("Job","group1").build();
        Trigger simpleTrigger= TriggerBuilder.newTrigger()
                .withIdentity("Trigger")
                .withSchedule(
                        DailyTimeIntervalScheduleBuilder.dailyTimeIntervalSchedule()
                                .startingDailyAt(TimeOfDay.hourAndMinuteOfDay(16, 42))//每天16：42开始
                                .endingDailyAt(TimeOfDay.hourAndMinuteOfDay(17, 0))//17：00 结束
                                .onDaysOfTheWeek(MONDAY,TUESDAY,WEDNESDAY,THURSDAY,FRIDAY)//周一至周五执行
                                .withIntervalInSeconds(1)//一秒一次
                                .withRepeatCount(100)//最多重复100次（实际执行100+1次
                )
                .build();
        StdSchedulerFactory factory = new StdSchedulerFactory();
        Scheduler scheduler = factory.getScheduler();
        System.out.println("现在的时间 ："+sf.format(new Date()));
        System.out.println();
        System.out.println("最近的一次执行时间 ："+sf.format(scheduler.scheduleJob(jobDetail,simpleTrigger))); //scheduler与jobDetail、trigger绑定，并打印出最近一次执行的事件
        scheduler.start();
    }
}
