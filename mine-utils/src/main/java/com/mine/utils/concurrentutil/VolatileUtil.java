package com.mine.utils.concurrentutil;

/**
 * Volatile关键字的使用,实时反映变量状态
 * 应用场景：状态变量、双重检查
 * Created by jiangqd on 2019/3/25.
 */
public class VolatileUtil {

    private static volatile boolean flag = false;

    public static void main(String[] args) throws InterruptedException {
        ThreadPoolUtil.THREAD_POOL_EXECUTOR.execute(() -> {
            while (!flag) {
                System.out.println("lll");
            }
        });
        //方法返回活动线程的当前线程的线程组中的数量
        System.out.println(Thread.activeCount()+"kadnjffadsfffffddddddddddddddddd");
        Thread.sleep(0);
        flag = true;
        ThreadPoolUtil.THREAD_POOL_EXECUTOR.shutdown();
        System.out.println("主线程停止");
    }
}
