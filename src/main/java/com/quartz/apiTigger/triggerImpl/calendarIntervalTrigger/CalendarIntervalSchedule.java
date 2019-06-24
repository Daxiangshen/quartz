package com.quartz.apiTigger.triggerImpl.calendarIntervalTrigger;

import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;

public class CalendarIntervalSchedule {
    public static void main(String[] args) throws SchedulerException {
        JobDetail jobDetail= JobBuilder.newJob(HelloJob.class).withIdentity("Job").build();
        //CalendarIntervalScheduleBuilder用法
        CalendarIntervalTrigger calendarIntervalTrigger=TriggerBuilder.newTrigger().withIdentity("Trigger")
                .withSchedule(CalendarIntervalScheduleBuilder.calendarIntervalSchedule().withIntervalInSeconds(1)).build();
        StdSchedulerFactory stdSchedulerFactory=new StdSchedulerFactory();
        Scheduler scheduler=stdSchedulerFactory.getScheduler();
        scheduler.start();
        scheduler.scheduleJob(jobDetail,calendarIntervalTrigger);
    }
}
