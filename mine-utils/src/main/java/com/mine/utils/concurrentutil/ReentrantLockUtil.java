package com.mine.utils.concurrentutil;

/**
 * 可重入锁和不可重入锁
 * 暂时记住ReentrantLock和synchornized是重入锁
 * 子类、父类同时加synchornized时，在先调用子类的时候加了锁，此时在子类中调用父类方法时，
 * 因为父类也有synchornized，会导致在锁内再次加锁，如果是非可重入锁就会导致死锁
 * 可重入锁与非可重入锁的区别在获取锁失败的同时是否判断占用该锁的线程是否就是自己
 * Created by jiangqd on 2019/3/25.
 */
public class ReentrantLockUtil {

    public static void main(String[] args) {
        for (int i = 0; i < 800; i++) {
            ThreadPoolUtil.THREAD_POOL_EXECUTOR.execute(() -> {
                ReentrantLockUtil reentrantLockUtil = new ReentrantLockUtil();
                LoggingWidget loggingWidget = reentrantLockUtil.new LoggingWidget();
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                loggingWidget.doSomething();
            });
        }
    }

    class Widget {
        public synchronized void doSomething() {
            System.out.println("kkkk");
        }
    }

    class LoggingWidget extends Widget {
        @Override
        public synchronized void doSomething() {
            System.out.println("1111");
            super.doSomething(); //这里调用父类的方法需要再次获得自己的锁，如果锁不可重入将导致死锁
        }
    }
}
