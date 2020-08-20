package com.mine.utils.concurrentutil;

/**
 * synchornized（可重入锁）的原理及使用，分为类锁和对象锁。其在底层通过指令monitorenter和monitorexit来加锁，
 * 在可以进入该对象时每次进入数加一，出去时减一，当进入数为0则放弃当前锁
 * 静态方法属于类而不是对象，所以在多线程的情况下其它对象实例也无法访问该类，从而互斥访问必须按顺序执行
 * 而在非静态多线程情况下，在一个示例情况下，线程必须竞争获取该对象锁才能执行，如果是多个对象则不需要考虑竞争获取锁问题
 * Created by jiangqd on 2019/3/25.
 */
public class SynchornizedUtil {

    public static void main(String[] args) {
        SynchornizedUtil test1 = new SynchornizedUtil();
        SynchornizedUtil test2 = new SynchornizedUtil();

        //普通不同步方法
//        new Thread(() -> test1.method1()).start();
//        new Thread(() -> test1.method2()).start();
        //普通同步方法
        new Thread(() -> test1.method3()).start();
        new Thread(() -> test1.method4()).start();
        //静态同步方法
//        new Thread(() -> method5()).start();
//        new Thread(() -> method6()).start();

    }

    public void method1() {
        System.out.println("Method 1 start");
        try {
            System.out.println("Method 1 execute");
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Method 1 end");
    }

    public void method2() {
        System.out.println("Method 2 start");
        try {
            System.out.println("Method 2 execute");
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Method 2 end");
    }

    public synchronized void method3() {
        System.out.println("Method 1 start");
        try {
            System.out.println("Method 1 execute");
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Method 1 end");
    }

    public synchronized void method4() {
        System.out.println("Method 2 start");
        try {
            System.out.println("Method 2 execute");
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Method 2 end");
    }

    public static synchronized void method5() {
        System.out.println("Method 1 start");
        try {
            System.out.println("Method 1 execute");
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Method 1 end");
    }

    public static synchronized void method6() {
        System.out.println("Method 2 start");
        try {
            System.out.println("Method 2 execute");
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Method 2 end");
    }

}
