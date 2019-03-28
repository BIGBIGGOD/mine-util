package com.mine.util.concurrentutil;

import com.google.common.util.concurrent.ThreadFactoryBuilder;
import com.mine.model.User;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * ThreadLocal的使用以及如何避免OOM的产生
 * ThreadLocal底层创建了一个ThreadLocalMap集合(一个定义在ThreadLocal类中的内部类)，集合中以当前线程对象作为key，因为线程池的线程可以重复利用，所以此线程并不会销毁，
 * 而ThreadLocalMap的生命周期和这个线程一样，所以当该线程任务结束回到线程池时，该键值对并不会被清除，需要需要加一些防护措施，
 * 在使用ThreadLocal的get(),set(),remove()的时候都会清除线程ThreadLocalMap里所有key为null的value
 * Created by jiangqd on 2019/3/22.
 */
public class ThreadLocalUtil {

    private static final int THREAD_POOL_SIZE = 534165400;

    private static final int LIST_SIZE = 1000;

    /**
     * 初始化ThreadLocal线程池
     */
    private static ThreadLocal<List<User>> threadLocal = new ThreadLocal<List<User>>(){
        @Override
        protected List<User> initialValue() {
            return new ArrayList<User>();
        }
    };

    private static final ThreadPoolExecutor THREAD_POOL_EXECUTOR = new ThreadPoolExecutor(
        Runtime.getRuntime().availableProcessors() * 2 + 1, 500, 0,
        TimeUnit.MILLISECONDS, new LinkedBlockingDeque<>(), new ThreadFactoryBuilder().setDaemon(false).setNameFormat("Thread-name-%d").build()
    );

    public static void main(String[] args) {
        try {
            for (int i = 0; i < THREAD_POOL_SIZE; i++) {
                int j = i;
                THREAD_POOL_EXECUTOR.execute(() -> {
                    threadLocal.set(getList());
                    Thread thread = Thread.currentThread();
                    System.out.println(thread.getName());
                    System.out.println("你好啊" + j);
                    //调用remove方法清除数据
                    threadLocal.remove();
                });
            }
        } catch (Exception e) {
            e.printStackTrace();
            return;
        }

    }

    public static List<User> getList() {
        List<User> list = new ArrayList<>(LIST_SIZE);
        for (int i = 0; i < list.size(); i++) {
            list.add(new User("name", "男", i));
        }
        return list;
    }

}
