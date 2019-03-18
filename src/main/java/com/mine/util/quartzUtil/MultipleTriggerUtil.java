package com.mine.util.quartzUtil;

import com.mine.util.quartzUtil.job.OrdinaryJob;
import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;

import java.util.Date;

/**
 * Created by jiangqd on 2019/2/14.
 */
public class MultipleTriggerUtil {

    public static void main(String[] args) throws InterruptedException, SchedulerException {
        //SimpleTrigger类型trigger的操作
        simpleTrigger();
        //CronTrigger类型trigger的操作
//        cronTrigger();

    }

    private static void simpleTrigger() throws SchedulerException, InterruptedException {

        Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();

        //方式一：设置时间将在8秒之后之后运行（还可以设置分钟）
        Date date1 = DateBuilder.nextGivenSecondDate(null, 8);

        //方式二：设置时间将在10秒之后之后运行（还可以设置分钟）
        Date  date2 = DateBuilder.futureDate(10, DateBuilder.IntervalUnit.SECOND);

        //设置运行次数以及运行间隔时间，默认为1和0
        SimpleTrigger trigger = (SimpleTrigger)TriggerBuilder.newTrigger()
            .withIdentity("ordinaryTrigger","ordinaryTriggerGroup")
            .startAt(date1)
            .withSchedule(SimpleScheduleBuilder.simpleSchedule().withIntervalInSeconds(2).withRepeatCount(3))
            .build();

        //设置运行次数为无限，次数会显示为0，运行间隔时间为2秒
        /*SimpleTrigger trigger = (SimpleTrigger)TriggerBuilder.newTrigger()
            .withIdentity("ordinaryTrigger","ordinaryTriggerGroup")
            .startAt(date1)
            .withSchedule(SimpleScheduleBuilder.simpleSchedule().withIntervalInSeconds(2).repeatForever())
            .build();*/

        JobDetail jobDetail = JobBuilder.newJob(OrdinaryJob.class)
            .withIdentity("ordinaryTriggerJob", "ordinaryTriggerGroup")
            .usingJobData("email", "admin@qq.com")
            .build();

        Date time = scheduler.scheduleJob(jobDetail,trigger);
        System.out.println("当前时间是：" + new Date().toLocaleString());
        System.out.printf("%s 这个任务会在 %s 准时开始运行，累计运行%d次，间隔时间是%d毫秒%n", jobDetail.getKey(), time.toLocaleString(), trigger.getRepeatCount()+1, trigger.getRepeatInterval());

        scheduler.start();
        Thread.sleep(20000);
        scheduler.shutdown(true);
    }

    private static void cronTrigger() throws SchedulerException, InterruptedException {

        Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();

        Date date = DateBuilder.nextGivenSecondDate(null,8);

        CronTrigger trigger = (CronTrigger)TriggerBuilder.newTrigger()
            .withIdentity("CronTrigger", "CronTriggerGroup")
            .startAt(date)
            .withSchedule(CronScheduleBuilder.cronSchedule("0/2 * * * * ?"))
            .build();

        JobDetail jobDetail = JobBuilder.newJob(OrdinaryJob.class)
            .withIdentity("CronTriggerJob","CronTriggerGroup")
            .usingJobData("email", "admin@qq.com")
            .build();

        Date time = scheduler.scheduleJob(jobDetail,trigger);

        System.out.println("使用的cron表达式是："+ trigger.getCronExpression());
        System.out.println(trigger.getKey()+"这个任务会在"+time.toLocaleString()+"开始执行");

        scheduler.start();

        Thread.sleep(20000);
        scheduler.shutdown(true);
    }

}
