package com.mine.utils.algorithmutil;

import java.util.Objects;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.LockSupport;

import lombok.extern.slf4j.Slf4j;

/**
 * @author jiangqingdong
 * @version v1.0
 * @Description 漏桶算法：用于限流
 * @DATE 2020/6/12 0012 14:54
 * @Copyright Copyright © 2019 深圳花儿绽放网络科技股份有限公司. All rights reserved.
 */
@Slf4j
public class BucketLimitUtil {
    public static class BucketLimit {
        static AtomicInteger threadNum = new AtomicInteger(1);
        //容量
        private final int capacity;
        //流速
        private final int flowRate;
        //流速时间单位
        private final TimeUnit flowRateUnit;
        // 阻塞队列
        private BlockingQueue<Node> queue;
        //漏桶流出的任务时间间隔（纳秒）
        private long flowRateNanosTime;

        public BucketLimit(int capacity, int flowRate, TimeUnit flowRateUnit) {
            this.capacity = capacity;
            this.flowRate = flowRate;
            this.flowRateUnit = flowRateUnit;
            this.bucketThreadWork();
        }

        //漏桶线程
        public void bucketThreadWork() {
            this.queue = new ArrayBlockingQueue<>(capacity);
            //漏桶流出的任务时间间隔（纳秒）
            this.flowRateNanosTime = flowRateUnit.toNanos(1) / flowRate;
            Thread thread = new Thread(this::bucketWork);
            thread.setName("漏桶线程-" + threadNum.getAndIncrement());
            thread.start();
        }

        //漏桶线程开始工作
        public void bucketWork() {
            while (true) {
                //poll 移除并返问队列头部的元素 如果队列为空，则返回null
                Node node = this.queue.poll();
                if (Objects.nonNull(node)) {
                    //唤醒任务线程
                    LockSupport.unpark(node.thread);
                }
                //休眠flowRateNanosTime
                LockSupport.parkNanos(this.flowRateNanosTime);
            }
        }

        //返回一个漏桶
        public static BucketLimit build(int capacity, int flowRate, TimeUnit flowRateUnit) {
            if (capacity <= 0 || flowRate <= 0) {
                throw new IllegalArgumentException("capacity、flowRate必须大于0！");
            }
            return new BucketLimit(capacity, flowRate, flowRateUnit);
        }

        //当前线程加入漏桶，返回false，表示漏桶已满；true：表示被漏桶限流成功，可以继续处理任务
        public boolean acquire() {
            Thread thread = Thread.currentThread();
            Node node = new Node(thread);
            // 队列操作,当队列已经满了offer操作会直接返回false否则true，put操作会阻塞直到插入成功，add操作会直接报错IllegalStateException
            if (this.queue.offer(node)) {
                //使用park阻塞线程
                LockSupport.park();
                return true;
            }
            return false;
        }

        //漏桶中存放的元素
        static class Node {
            private Thread thread;

            public Node(Thread thread) {
                this.thread = thread;
            }
        }
    }

    public static void main(String[] args) {
        BucketLimit bucketLimit = BucketLimit.build(10, 60, TimeUnit.MINUTES);
        for (int i = 0; i < 15; i++) {
            new Thread(() -> {
                boolean acquire = bucketLimit.acquire();
                log.info("是否进入队列，time={},res={}", System.currentTimeMillis(), acquire);
                try {
                    //相当于包装过的Thread.sleep(ms, ns)
                    TimeUnit.SECONDS.sleep(1);
                } catch (Exception e) {
                    log.error("限流出错，e", e);
                }
            }).start();
        }
    }
}
