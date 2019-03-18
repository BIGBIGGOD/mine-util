package com.mine.util.quartzUtil;

import com.mine.util.quartzUtil.job.ConcurrentJob;
import com.mine.util.quartzUtil.job.ExceptionJob1;
import com.mine.util.quartzUtil.job.ExceptionJob2;
import com.mine.util.quartzUtil.job.OrdinaryJob;
import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;

/**
 * Created by jiangqd on 2019/2/14.
 */
public class MultipleJobUtil {

    public static void main(String[] args) throws InterruptedException, SchedulerException {
        //执行普通job
        ordinaryJob();
        //执行时避免并发的job
        concurrentJob();
        //执行时取消发生异常job的调度
        exceptionJob1();
        //job异常时，修改参数并立即重新执行
        exceptionJob2();
    }

    private static void ordinaryJob() throws SchedulerException, InterruptedException {

        Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();

        Trigger trigger = TriggerBuilder.newTrigger().withIdentity("ordinaryJobTrigger", "ordinaryJobGroup")
            .startNow()
            .withSchedule(SimpleScheduleBuilder
                .simpleSchedule()
                .withRepeatCount(5)
                .withIntervalInSeconds(2))
            .build();

        JobDetail jobDetail = JobBuilder.newJob(OrdinaryJob.class)
            .withIdentity("ordinaryJob", "ordinaryJobGroup")
            .usingJobData("email", "admin@11056.com")
            .build();

        //改变map中key为“email”的值
        jobDetail.getJobDataMap().put("email","admin@taobao.com");

        scheduler.scheduleJob(jobDetail,trigger);

        scheduler.start();

        Thread.sleep(20000);
        scheduler.shutdown(true);
    }

    private static void concurrentJob() throws SchedulerException, InterruptedException {

        Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();

        Trigger trigger = TriggerBuilder.newTrigger().withIdentity("concurrentJobTrigger", "concurrentJobGroup")
            .startNow()
            .withSchedule(SimpleScheduleBuilder
                .simpleSchedule()
                .withRepeatCount(5)
                .withIntervalInSeconds(2))
            .build();
        JobDetail jobDetail = JobBuilder.newJob(ConcurrentJob.class)
            .withIdentity("ConcurrentJob", "concurrentJobGroup")
            .usingJobData("database", "某某数据库")
            .build();

        scheduler.scheduleJob(jobDetail,trigger);

        scheduler.start();

        Thread.sleep(20000);
        scheduler.shutdown(true);
    }

    private static void exceptionJob1() throws SchedulerException, InterruptedException {

        Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();

        Trigger trigger = TriggerBuilder.newTrigger().withIdentity("exceptionJob1Trigger", "exceptionJob1Group")
            .startNow()
            .withSchedule(SimpleScheduleBuilder
                .simpleSchedule()
                .withRepeatCount(5)
                .withIntervalInSeconds(2))
            .build();
        JobDetail jobDetail = JobBuilder.newJob(ExceptionJob1.class)
            .withIdentity("exceptionJob1", "exceptionJob1Group")
            .build();

        scheduler.scheduleJob(jobDetail,trigger);

        scheduler.start();

        Thread.sleep(20000);
        scheduler.shutdown(true);
    }

    private static void exceptionJob2() throws SchedulerException, InterruptedException {

        Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();

        Trigger trigger = TriggerBuilder.newTrigger().withIdentity("exceptionJob2Trigger", "exceptionJob2Group")
            .startNow()
            .withSchedule(SimpleScheduleBuilder
                .simpleSchedule()
                .withRepeatCount(5)
                .withIntervalInSeconds(2))
            .build();
        JobDetail jobDetail = JobBuilder.newJob(ExceptionJob2.class)
            .withIdentity("exceptionJob2", "exceptionJob2Group")
            .build();

        scheduler.scheduleJob(jobDetail,trigger);

        scheduler.start();

        Thread.sleep(20000);
        scheduler.shutdown(true);
    }

}
