package com.mine.util.quartzUtil.listener;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.JobListener;

/**
 * Created by jiangqd on 2019/2/14.
 */
public class OrdinaryJobListener implements JobListener {

    //该方法返回一个字符串用以说明JobListener的名称，
    // 对于注册于全局的监听器,getName()主要用于日志，对于特定Job引用的JobListener，
    // 注册在JobDetail上的监听器名称必须匹配从监听器上getName()方法的返回值
    @Override
    public String getName() {
        return "Listener for some Job";
    }

    //在JobDetail将要执行时调用该方法
    @Override
    public void jobToBeExecuted(JobExecutionContext jobExecutionContext) {
        System.out.println("将要执行：\t "+jobExecutionContext.getJobDetail().getKey());
    }

    //在JobDetail将要执行却又被TriggerListener否定时调用该方法
    @Override
    public void jobExecutionVetoed(JobExecutionContext jobExecutionContext) {
        System.out.println("准备执行：\t "+jobExecutionContext.getJobDetail().getKey());
    }

    //在Jobtail执行之后调用该方法
    @Override
    public void jobWasExecuted(JobExecutionContext jobExecutionContext, JobExecutionException e) {
        System.out.println("执行结束：\t "+jobExecutionContext.getJobDetail().getKey());
    }
}
