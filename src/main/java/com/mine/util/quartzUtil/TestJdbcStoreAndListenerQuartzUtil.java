package com.mine.util.quartzUtil;

import com.mine.util.quartzUtil.job.OrdinaryJob;
import com.mine.util.quartzUtil.listener.OrdinaryJobListener;
import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;
import org.quartz.impl.matchers.KeyMatcher;

import static org.quartz.JobBuilder.newJob;
import static org.quartz.SimpleScheduleBuilder.simpleSchedule;
import static org.quartz.TriggerBuilder.newTrigger;

/**
 * Created by jiangqd on 2019/2/14.
 * 再次测试jdbcStore和listener
 * 创建quartz数据库，并在其中创建默认的11张表
 * 创建quartz.properties文件，在其中配置数据库连接信息
 * 创建空白调度器schedule，当在数据库中发现存在任务时抛出ObjectAlreadyExistsException异常，并在其后捕获该异常，然后执行空白调度器
 * 创建某listener类实现JobListener接口，然后在schedule的类中增加Job监听
 */
public class TestJdbcStoreAndListenerQuartzUtil {

    public static void main(String[] args) throws Exception {
        try {
            method1();
        }catch (ObjectAlreadyExistsException e){
            System.out.println("发现任务已经在数据库存在了，直接从数据库执行："+e.getMessage());
            method2();
        }
    }

    private static void method1() throws SchedulerException, InterruptedException {

        //创建调度器
        Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();

        //定义一个触发器
        Trigger trigger = newTrigger().withIdentity("trigger1", "group1") //定义名称和所属的组
            .startNow()
            .withSchedule(simpleSchedule()
                .withIntervalInSeconds(15) //每隔n秒执行一次
                .withRepeatCount(10)) //总共执行m次(第一次执行不基数)
            .build();

        //定义一个JobDetail
        JobDetail job = newJob(OrdinaryJob.class) //指定干活的类MailJob
            .withIdentity("OrdinaryJob", "OrdinaryJobGroup") //定义任务名称和分组
            .usingJobData("email", "admin@10086.com") //定义属性
            .build();

        //改变map中key为“email”的值
        job.getJobDataMap().put("email","admin@taobao.com");

        //增加Job监听
        OrdinaryJobListener ordinaryJobListener = new OrdinaryJobListener();
        KeyMatcher<JobKey> keyKeyMatcher = KeyMatcher.keyEquals(job.getKey());
        scheduler.getListenerManager().addJobListener(ordinaryJobListener,keyKeyMatcher);

        //调度加入这个job
        scheduler.scheduleJob(job, trigger);

        //启动
        scheduler.start();

        //等待20秒，让前面的任务都执行完了之后，再关闭调度器
        Thread.sleep(20000);
        scheduler.shutdown(true);
    }

    private static void method2() throws Exception {
        Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();
        scheduler.start();
        // 等待200秒，让前面的任务都执行完了之后，再关闭调度器
        Thread.sleep(20000);
        scheduler.shutdown(true);
    }

}
