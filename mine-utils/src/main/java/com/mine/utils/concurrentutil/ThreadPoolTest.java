package com.mine.utils.concurrentutil;

import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import org.apache.commons.lang3.concurrent.BasicThreadFactory;
import org.junit.Test;

import lombok.extern.slf4j.Slf4j;

/**
 * Created by jiangqd on 2019/3/13.
 */
@Slf4j
public class ThreadPoolTest {

    private static final int POOL_SIZE = 40;//线程池大小

    //订单任务线程池
    private static ThreadPoolExecutor comitTaskPool = (ThreadPoolExecutor) new ScheduledThreadPoolExecutor(POOL_SIZE,
            new BasicThreadFactory.Builder().namingPattern("example-schedule-pool-%d").daemon(true).build());

    /**
     * 执行订单任务
     */
    @Test
    public void test1() {
        System.out.println(Thread.currentThread().getName());
        Runnable comitTask = new Runnable() {
            @Override
            public void run() {
                System.out.println(Thread.currentThread().getName());
                System.out.println("你好啊");
            }
        };
        comitTaskPool.execute(comitTask);
        System.out.println(Thread.currentThread().getName());
        log.debug("【线程池任务】线程池中线程数：" + comitTaskPool.getPoolSize());
        log.debug("【线程池任务】队列中等待执行的任务数：" + comitTaskPool.getQueue().size());
        log.debug("【线程池任务】已执行完任务数：" + comitTaskPool.getCompletedTaskCount());
        System.out.println(Thread.currentThread().getName());
        shutdown();
    }

    /**
     * 关闭线程池
     */
    public void shutdown() {
        System.out.println(Thread.currentThread().getName());
        log.debug("shutdown comitTaskPool...");
        comitTaskPool.shutdown();
        try {
            if (!comitTaskPool.isTerminated()) {
                log.debug("直接关闭失败[" + comitTaskPool.toString() + "]");
                comitTaskPool.awaitTermination(3, TimeUnit.SECONDS);
                if (comitTaskPool.isTerminated()) {
                    log.debug("成功关闭[" + comitTaskPool.toString() + "]");
                } else {
                    log.debug("[" + comitTaskPool.toString() + "]关闭失败，执行shutdownNow...");
                    if (comitTaskPool.shutdownNow().size() > 0) {
                        log.debug("[" + comitTaskPool.toString() + "]没有关闭成功");
                    } else {
                        log.debug("shutdownNow执行完毕，成功关闭[" + comitTaskPool.toString() + "]");
                    }
                }
            } else {
                log.debug("成功关闭[" + comitTaskPool.toString() + "]");
            }
        } catch (InterruptedException e) {
            log.warn("接收到中断请" + comitTaskPool.toString() + "停止操作");
        }
    }

}
