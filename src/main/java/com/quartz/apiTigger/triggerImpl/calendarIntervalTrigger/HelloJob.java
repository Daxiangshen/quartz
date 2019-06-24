package com.quartz.apiTigger.triggerImpl.calendarIntervalTrigger;

import org.quartz.*;

import java.text.SimpleDateFormat;
import java.util.Date;

public class HelloJob implements Job {
    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        Date date=new Date();
        SimpleDateFormat sf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        System.err.println("现在的时间是；"+sf.format(date));
        System.err.println("go");
    }
}
