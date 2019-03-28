package com.mine.util.concurrentutil;

import com.google.common.util.concurrent.ThreadFactoryBuilder;

import java.util.concurrent.*;

/**
 * 线程池工具类
 * Created by jiangqd on 2019/3/25.
 */
public class ThreadPoolUtil {

    /**
     * 当前服务器cpu核心数
     */
    private static final int CPU_SIZE = Runtime.getRuntime().availableProcessors();

    /**
     * 自定义创建线程池
     */
    public static final ThreadPoolExecutor THREAD_POOL_EXECUTOR = new ThreadPoolExecutor(CPU_SIZE*2+1, 500, 0,
        TimeUnit.MILLISECONDS, new LinkedBlockingDeque<>(), new ThreadFactoryBuilder().setDaemon(false).setNameFormat("Thread-name-%d").build()
    );

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

}
