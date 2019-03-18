package com.mine.util.quartzUtil.job;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

/**
 * Created by jiangqd on 2019/2/14.
 */
public class ExceptionJob2 implements Job {

    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {

        int i = 0;
        try {
            System.out.println(100/i);
        }catch (Exception e){
            System.out.println("发生异常，修改参数，立即重新执行");
            i = 1;
            JobExecutionException jobExecutionException = new JobExecutionException(e);
            jobExecutionException.setRefireImmediately(true);
            throw jobExecutionException;
        }
    }
}
