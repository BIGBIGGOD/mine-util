package com.mine.util.concurrentutil;

/**
 * 简单的死锁实现
 * 在这里创建了两个对象和两个线程，通过使两个线程各自持有一个对象的锁同时还需要获取对方的锁从而造成保持并请求死锁
 * Created by jiangqd on 2019/3/28.
 */
public class DeadLockUtil {

    public static void main(String[] args) {

        final DeadLockUtil dd1 = new DeadLockUtil();
        final DeadLockUtil dd2 = new DeadLockUtil();

        Thread thread1 = new Thread(() -> {
            //首先获得dd1的锁
            synchronized (dd1) {
                //休眠
                try {
                    Thread.sleep(50);
                    synchronized (dd2) {
                        System.out.println(Thread.currentThread().getName() + "线程。。");
//                        dd2.notify();
                    }
                } catch (InterruptedException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        }, "thread1");

        Thread thread2 = new Thread(() -> {
            synchronized (dd2) {
                try {
//                    dd2.wait();
                    synchronized (dd1) {
                        System.out.println(Thread.currentThread().getName() + "线程。。");
                    }
                } catch (Exception e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        }, "thread2");

        thread1.start();
        thread2.start();
    }
}
