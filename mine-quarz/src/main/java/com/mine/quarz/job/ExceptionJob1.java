package com.mine.quarz.job;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

/**
 * Created by jiangqd on 2019/2/14.
 */
public class ExceptionJob1 implements Job {

    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {

        int i = 0;
        try {
            System.out.println(100/i);
        }catch (Exception e){
            System.out.println("发生异常，取消这个job的所有定时调度");
            JobExecutionException jobExecutionException = new JobExecutionException(e);
            jobExecutionException.setUnscheduleAllTriggers(true);
            throw jobExecutionException;
        }
    }
}
