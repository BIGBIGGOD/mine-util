package com.mine.quarz.job;

import org.quartz.Job;
import org.quartz.JobDetail;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by jiangqd on 2019/2/14.
 */
public class OrdinaryJob implements Job {

    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {

        JobDetail detail = context.getJobDetail();
        String email = detail.getJobDataMap().getString("email");

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String now = sdf.format(new Date());
        System.out.println("给邮件地址"+email+" 发出了一封定时邮件, 当前时间是: "+now);
    }

}
