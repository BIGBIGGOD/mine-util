package com.mine.util.concurrentutil;

import java.util.Random;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * CyclicBarrier控制其它线程的执行，以CyclicBarrier的parties为基准，当为0的时候还有其它线程等待执行则继续新一轮执行，否则结束
 * 注意主线程一直在执行
 * Created by jiangqd on 2019/3/22.
 */
public class CyclicBarrierUtil {

    public static void main(String[] args) {
        CyclicBarrier cyc = new CyclicBarrier(3);
        for (int i = 1; i <= cyc.getParties(); i++) {
            //传统方法
//            new Thread(new MyThread(cyc), "第" + i + "个玩家").start();
            //使用lambda表达式
            new Thread(() ->{
                for (int j = 1; j <= 3; j++) {
                    try {
                        long time = new Random().nextInt(2001) + 1000;
                        Thread.sleep(time);
                        System.out.println(Thread.currentThread().getName() + "通过了第" + j + "个障碍物，用时" + time + "s");
                        cyc.await();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } catch (BrokenBarrierException e) {
                        e.printStackTrace();
                    }
                }
            }, "第" + i + "个玩家").start();
        }
        System.out.println("游戏结束");
    }

    private static class MyThread implements Runnable {

        private CyclicBarrier cyc;

        public MyThread(CyclicBarrier cyc) {
            this.cyc = cyc;
        }

        @Override
        public void run() {
            for (int i = 1; i <= 3; i++) {
                try {
                    long time = new Random().nextInt(2001) + 1000;
                    Thread.sleep(time);
                    System.out.println(Thread.currentThread().getName() + "通过了第" + i + "个障碍物，用时" + time + "s");
                    cyc.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (BrokenBarrierException e) {
                    e.printStackTrace();
                }
            }
        }
    }

}
