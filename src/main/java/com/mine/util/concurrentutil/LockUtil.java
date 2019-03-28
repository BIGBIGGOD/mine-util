package com.mine.util.concurrentutil;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Lock的使用以及和synchornized的区别
 * Created by jiangqd on 2019/3/25.
 */
public class LockUtil {

    private Lock lock = new ReentrantLock();

    public static void main(String[] args) {
        LockUtil lockUtil = new LockUtil();
            new Thread(() -> lockUtil.method2(Thread.currentThread()), "thread1").start();
            new Thread(() -> lockUtil.method2(Thread.currentThread()), "thread2").start();
    }

    public void method1(Thread thread) {
        lock.lock();
        try {
            System.out.println("线程名称为："+thread.getName()+"获取当前锁");
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            System.out.println("线程"+thread.getName()+"执行完释放锁");
            lock.unlock();
        }
    }

    /**
     * 尝试获取锁
     */
    public void method2(Thread thread) {
        if (lock.tryLock()) {
            lock.lock();
            try {
                System.out.println("线程名称为："+thread.getName()+"获取当前锁");
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }finally {
                System.out.println("线程"+thread.getName()+"执行完释放锁");
                lock.unlock();
            }
        }else {
            System.out.println("我是线程"+thread.getName()+",当前锁被占用，我无法获取");
        }
    }

    /**
     * 设置获取锁的时间
     */
    public void method3(Thread thread) throws InterruptedException {
        if (lock.tryLock(3000, TimeUnit.MILLISECONDS)) {
            lock.lock();
            try {
                System.out.println("线程名称为："+thread.getName()+"获取当前锁");
                Thread.sleep(2000);
            } catch (Exception e) {
                e.printStackTrace();
            }finally {
                System.out.println("线程"+thread.getName()+"执行完释放锁");
                lock.unlock();
            }
        }else {
            System.out.println("我是线程"+thread.getName()+",当前锁被占用，我无法获取");
        }
    }
}
