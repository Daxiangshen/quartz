package com.quartz.apiJob;

import org.quartz.*;

import java.text.SimpleDateFormat;
import java.util.Date;

public class HelloJob implements Job {
    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        Date date=new Date();
        SimpleDateFormat sf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        System.err.println("现在的时间是："+sf.format(date));
        //业务逻辑
        System.err.println("go");
        JobKey key=jobExecutionContext.getJobDetail().getKey();
        System.err.println("jobDetail的name："+key.getName());
        System.err.println("jobDetail的group："+key.getGroup());
        JobDataMap jobDetailDataMap=jobExecutionContext.getJobDetail().getJobDataMap();
        String message=jobDetailDataMap.getString("message");
        float floatJobValue=jobDetailDataMap.getFloat("FloatJobValue");
        System.err.println("JobDataMap定义的message的值："+message);
        System.err.println("jobDataMap定义的floatJobValue的值： "+floatJobValue);
    }
}
