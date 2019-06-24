package com.quartz.apiTigger.baseCalndar;

import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;
import org.quartz.impl.calendar.AnnualCalendar;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;

public class CalendarSchedule {
    public static void main(String[] args) throws SchedulerException {
        SimpleDateFormat sf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        JobDetail jobDetail= JobBuilder.newJob(HelloJob.class).withIdentity("Job").build();
        //AnnualCalendar:排除每一年中指定的一天或者多少天 ，精度是天
        AnnualCalendar holidays=new AnnualCalendar();
        // 排除今天的时间2019年6月24日!!!!!!!!!!!!!!!!!!!!注意了！（月份是从0～11的）
        GregorianCalendar nationalDay=new GregorianCalendar(2019,5,24);
        //排除的日期，如果为false则为包含
        holidays.setDayExcluded(nationalDay,true);
        StdSchedulerFactory factory=new StdSchedulerFactory();
        Scheduler scheduler=factory.getScheduler();
        //向Scheduler注册日历
        scheduler.addCalendar("holidays",holidays,false,false);
        Trigger simpleTrigger=TriggerBuilder.newTrigger()
                .withIdentity("Trigger")
                .withSchedule(SimpleScheduleBuilder.simpleSchedule().withIntervalInSeconds(1).repeatForever())
                //将我们设置好的Calender与trigger绑定
                .modifiedByCalendar("holidays").build();
        //让trigger应用指定的日历规则
        System.err.println("现在的时间 ："+sf.format(new Date()));
        System.err.println("最近的一次执行时间 ："+sf.format(scheduler.scheduleJob(jobDetail,simpleTrigger))); //scheduler与jobDetail、trigger绑定，并打印出最近一次执行的事件
        scheduler.start();
    }
}
