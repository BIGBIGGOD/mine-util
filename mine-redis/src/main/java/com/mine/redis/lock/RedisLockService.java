package com.mine.redis.lock;

import java.util.concurrent.TimeUnit;

/**
 * 分布式锁
 * @author zhujialiang
 * @Date 2019/9/19 00:29
 */
public interface RedisLockService {

    /**
     * 获取锁 如果锁可用 立即返回true 否则返回false
     * @param key
     * @return
     */
    boolean tryLock(String key);

    /**
     * 锁在给定的等待时间内空闲 则获取锁成功 返回true 否则返回false
     * @param key
     * @param timeout
     * @param unit
     * @return
     */
    boolean tryLock(String key, long timeout, TimeUnit unit);

    /**
     * 释放锁
     * @param key
     */
    void unLock(String key);

    /**
     * 加锁
     * @param key
     * @param value 当前线程操作时的 System.currentTimeMillis() + 2000，2000是超时时间，这个地方不需要去设置redis的expire，
     *              也不需要超时后手动去删除该key，因为会存在并发的线程都会去删除，造成上一个锁失效，结果都获得锁去执行
     * @return
     */
    boolean lock(String key, String value);

    /**
     * 解锁
     * @param key
     * @param value
     */
    void unlock(String key, String value);
}
