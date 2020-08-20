package com.mine.quarz.job;

import org.quartz.*;

/**
 * Created by jiangqd on 2019/2/14.
 */
@DisallowConcurrentExecution
public class ConcurrentJob implements Job {

    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {

        JobDetail jobDetail = jobExecutionContext.getJobDetail();
        String database = jobDetail.getJobDataMap().getString("database");
        System.out.println("给数据库"+database+"备份,耗时3秒");
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
