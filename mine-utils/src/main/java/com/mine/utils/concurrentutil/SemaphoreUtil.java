package com.mine.utils.concurrentutil;

import com.google.common.util.concurrent.ThreadFactoryBuilder;
import lombok.extern.slf4j.Slf4j;

import java.time.Clock;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.Semaphore;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * Semaphore是一种计数信号量，是一种在多线程环境下使用的设施，该设施负责协调各个线程，以保证它们能够正确、合理的使用公共资源的设施,控制进程同步互斥的量。
 * 通过计数新号量控制可运行进程数也就是控制资源利用
 * 内部通过维护permits数量以及链表实现逻辑，和countdownLatch类似
 * @author jiangqd
 * @date 2019/3/22
 */
@Slf4j
public class SemaphoreUtil {

    private static final Semaphore SEMAPHORE = new Semaphore(3);

    private static final ThreadPoolExecutor THREAD_POOL_EXECUTOR = new ThreadPoolExecutor(
        Runtime.getRuntime().availableProcessors() * 2, 32, 0, TimeUnit.MILLISECONDS,
        new LinkedBlockingDeque<>(), new ThreadFactoryBuilder()
        .setDaemon(false)
        .setNameFormat("Thread-pool-%d")
        .setUncaughtExceptionHandler((t, e) -> log.info("There are Exception,t={},e={}", t, e))
        .build()
    );

    public static void main(String[] args) {
        String[] nameArr = {"李明", "王五", "张杰", "王强", "赵二", "李四", "张三"};
        int[] ageArr = {26, 27, 33, 45, 19, 23, 41};
        for (int i = 0; i < nameArr.length; i++) {
            String name = nameArr[i];
            int age = ageArr[i];
            THREAD_POOL_EXECUTOR.execute(getRunnable(name, age));
        }

    }

    public static Runnable getRunnable(String name, int age) {
        Runnable runnable = () -> {
            try {
                SEMAPHORE.acquire();
                System.out.println(Thread.currentThread().getName() + "大家好，我是" + name + ",我今年" + age + "岁");
                Thread.sleep(1000);
                System.out.println("准备释放许可,当前时间是" + Clock.systemDefaultZone().millis());
                System.out.println("当前可使用的许可数为：" + SEMAPHORE.availablePermits());
                SEMAPHORE.release();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        };
        return runnable;
    }

}
