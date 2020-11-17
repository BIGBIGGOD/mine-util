package com.mine.utils.concurrentutil;

import java.util.concurrent.Exchanger;

/**
 * Exchanger类可用于两个线程之间交换信息
 * Created by jiangqd on 2019/3/22.
 */
public class ExchangerUtil {

    private static Exchanger<String> exchanger = new Exchanger<>();

    public static void main(String[] args) {
        ThreadPoolUtil.THREAD_POOL_EXECUTOR.execute(getRunnable("222"));
        ThreadPoolUtil.THREAD_POOL_EXECUTOR.execute(getRunnable("111"));
        ThreadPoolUtil.THREAD_POOL_EXECUTOR.shutdown();

    }

    public static Runnable getRunnable(String str) {
        Runnable runnable = () -> {
            try {
                System.out.println(Thread.currentThread().getName() + ":" + exchanger.exchange(str));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        };
        return runnable;
    }
}
