package com.mine.util.concurrentutil;

import java.util.Random;
import java.util.concurrent.CountDownLatch;

/**
 * 使用concurrent包中的CounDowanLatch方法实现线程的管理
 * CountDownLatch利用减法计数，当最后为0的时候主线程才能继续往下执行，此时其它线程还会执行
 * 以英雄联盟游戏加载为例
 * Created by jiangqd on 2019/3/22.
 */
public class CountDownLatchUtil {

    public static void main(String[] args) throws InterruptedException {
        CountDownLatch latch = new CountDownLatch(3);
        for (int i = 0; i < latch.getCount(); i++) {
            //传统方式
            new Thread(new MyThread(latch), "玩家"+(i+1)).start();
            //使用jdk8特性
//            Runnable runnable = () -> {
//                try {
//                    int time = new Random().nextInt(2001) + 1000;
//                    Thread.sleep(time);
//                    System.out.println(Thread.currentThread().getName() + "已经准备好了，所用时间为" + (double) time / 1000 + "s");
//                    System.out.println(latch.getCount());
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }finally {
//                    latch.countDown();
//                }
//            };
//            new Thread(runnable, "玩家"+(i+1)).start();
        }
        System.out.println("正在等待所有玩家准备好!");
        //等待latch中计数为0
        latch.await();
        System.out.println("开始游戏!!");
    }

    private static class MyThread implements Runnable {

        private CountDownLatch latch;

        public MyThread(CountDownLatch latch) {
            this.latch = latch;
        }

        @Override
        public void run() {
            try {
                int time = new Random().nextInt(2001) + 1000;
                Thread.sleep(time);
                System.out.println(Thread.currentThread().getName() + "已经准备好了，所用时间为" + (double) time / 1000 + "s");
                System.out.println(latch.getCount());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }finally {
                latch.countDown();
            }
        }
    }
}

