package com.mine.util.concurrentutil;

import java.lang.management.ManagementFactory;
import java.lang.management.ThreadInfo;
import java.lang.management.ThreadMXBean;

import static com.mine.util.concurrentutil.ThreadPoolUtil.THREAD_POOL_EXECUTOR;

/**
 * ThreadLocal的使用以及如何避免OOM的产生
 * ThreadLocal底层创建了一个ThreadLocalMap集合(一个定义在ThreadLocal类中的内部类)，集合中以当前线程对象作为key，因为线程池的线程可以重复利用，所以此线程并不会销毁，
 * 而ThreadLocalMap的生命周期和这个线程一样，所以当该线程任务结束回到线程池时，该键值对并不会被清除，需要需要加一些防护措施，
 * 在使用ThreadLocal的get(),set(),remove()的时候都会清除线程ThreadLocalMap里所有key为null的value
 *
 * @author jiangqd
 * @date 2019/3/22
 */
public class ThreadLocalUtil {

    private static final int THREAD_POOL_SIZE = 534165400;

    /**
     * 初始化ThreadLocal线程池
     */
    private static ThreadLocal<String> threadLocal = ThreadLocal.withInitial(() -> "初始化");

    public static void main(String[] args) {
        ThreadMXBean threadMXBean = ManagementFactory.getThreadMXBean();
        ThreadInfo[] threadInfos = threadMXBean.dumpAllThreads(false,false);
        try {
            for (int i = 0; i < THREAD_POOL_SIZE; i++) {
                final int j = i;
                THREAD_POOL_EXECUTOR.execute(() -> {
                    System.out.println("初始" + threadLocal.get());
                    threadLocal.set("变化" + j);
                    System.out.println("线程名" + Thread.currentThread().getName());
                    System.out.println("排序" + j);
                    //调用remove方法清除数据
                    threadLocal.remove();
                });
            }
        } catch (Exception e) {
            e.printStackTrace();
            return;
        }
    }
}
