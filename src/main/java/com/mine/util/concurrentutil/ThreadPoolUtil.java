package com.mine.util.concurrentutil;

import com.google.common.util.concurrent.ThreadFactoryBuilder;

import java.util.concurrent.*;

/**
 * 线程池工具类
 * 线程池内部执行任务时首先判断线程数
 * @author jiangqd
 * @date 2019/3/25
 */
public class ThreadPoolUtil {

    /**
     * 当前服务器cpu核心数
     */
    private static final int CPU_SIZE = Runtime.getRuntime().availableProcessors();

    /**
     * 自定义创建线程池
     * 核心线程数量：看是否多为IO任务，一般设置为当前服务器cpu核心数*2+1、
     * 最大线程数量：根据业务所需设置、
     * 线程池维护线程所允许的空闲时间：根据业务所需设置、
     * 时间单位：根据业务所需设置、
     * 线程池队列：有四种，根据业务所需设置、
     * 指定创建线程的工厂：根据业务所需设置，主要设置线程名、
     * 线程池对拒绝任务(无线程可用)的处理策略：暂无
     */
    public static final ThreadPoolExecutor THREAD_POOL_EXECUTOR = new ThreadPoolExecutor(CPU_SIZE*2+1, 500, 0,
        TimeUnit.MILLISECONDS, new LinkedBlockingDeque<>(), new ThreadFactoryBuilder().setDaemon(false).setNameFormat("Thread-name-%d").build()
    );

    /**
     * 四种拒绝策略
     *ThreadPoolExecutor.AbortPolicy:丢弃任务并抛出RejectedExecutionException异常。
     * ThreadPoolExecutor.DiscardPolicy：也是丢弃任务，但是不抛出异常。
     * ThreadPoolExecutor.DiscardOldestPolicy：丢弃队列最前面的任务，然后重新尝试执行任务（重复此过程）
     * ThreadPoolExecutor.CallerRunsPolicy：由调用线程处理该任务
     */

    /**
     * 通过内置四种方式创建线程池
     * Executor接口表示线程池，其executor方法用来执行Runnable类型任务，用shutdown方法关闭线程池
     * 将线程的启动、执行、关闭都交给Executor来管理，舍弃以前Thread.start()方式
     */
    ExecutorService executorService1 = Executors.newFixedThreadPool(CPU_SIZE * 2 + 1, new CommonThreadFactory("Thread-"));
    ExecutorService executorService2 = Executors.newSingleThreadExecutor(new CommonThreadFactory("Thread-"));
    ExecutorService executorService3 = Executors.newScheduledThreadPool(CPU_SIZE * 2 + 1, new CommonThreadFactory("Thread-"));
    ExecutorService executorService4 = Executors.newCachedThreadPool(new CommonThreadFactory("Thread-"));

    /**
     * 利用构造方法传入线程名，同时创建返回一个Thread
     */
    private static class CommonThreadFactory implements ThreadFactory {

        private static int index = 0;

        final String prefix;

        CommonThreadFactory(String prefix) {
            this.prefix = prefix;
        }

        @Override
        public Thread newThread(Runnable r) {
            return new Thread(r, prefix + (index++));
        }
    }

//    ExecutorService是线程池接口。它定义了4中线程池：
//
//1. newCachedThreadPool：
//    底层：返回ThreadPoolExecutor实例，corePoolSize为0；maximumPoolSize为Integer.MAX_VALUE；keepAliveTime为60L；unit为TimeUnit.SECONDS；workQueue为SynchronousQueue(同步队列)
//    通俗：当有新任务到来，则插入到SynchronousQueue中，由于SynchronousQueue是同步队列，因此会在池中寻找可用线程来执行，若有可以线程则执行，若没有可用线程则创建一个线程来执行该任务；若池中线程空闲时间超过指定大小，则该线程会被销毁。
//    适用：执行很多短期异步的小程序或者负载较轻的服务器
//2. newFixedThreadPool：
//    底层：返回ThreadPoolExecutor实例，接收参数为所设定线程数量nThread，corePoolSize为nThread，maximumPoolSize为nThread；keepAliveTime为0L(不限时)；unit为：TimeUnit.MILLISECONDS；WorkQueue为：new LinkedBlockingQueue<Runnable>() 无界阻塞队列
//    通俗：创建可容纳固定数量线程的池子，每隔线程的存活时间是无限的，当池子满了就不在添加线程了；如果池中的所有线程均在繁忙状态，对于新任务会进入阻塞队列中(无界的阻塞队列)，但是，在线程池空闲时，即线程池中没有可运行任务时，它不会释放工作线程，还会占用一定的系统资源。
//    适用：执行长期的任务，性能好很多
//3. newSingleThreadExecutor:
//    底层：FinalizableDelegatedExecutorService包装的ThreadPoolExecutor实例，corePoolSize为1；maximumPoolSize为1；keepAliveTime为0L；unit为：TimeUnit.MILLISECONDS；workQueue为：new LinkedBlockingQueue<Runnable>() 无界阻塞队列
//    通俗：创建只有一个线程的线程池，且线程的存活时间是无限的；当该线程正繁忙时，对于新任务会进入阻塞队列中(无界的阻塞队列)
//    适用：一个任务一个任务执行的场景
//4. NewScheduledThreadPool:
//    底层：创建ScheduledThreadPoolExecutor实例，corePoolSize为传递来的参数，maximumPoolSize为Integer.MAX_VALUE；keepAliveTime为0；unit为：TimeUnit.NANOSECONDS；workQueue为：new DelayedWorkQueue() 一个按超时时间升序排序的队列
//    通俗：创建一个固定大小的线程池，线程池内线程存活时间无限制，线程池可以支持定时及周期性任务执行，如果所有线程均处于繁忙状态，对于新任务会进入DelayedWorkQueue队列中，这是一种按照超时时间排序的队列结构
//    适用：周期性执行任务的场景

}
