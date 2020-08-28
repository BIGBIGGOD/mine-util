package com.mine.redis.lock;

import java.util.Arrays;
import java.util.concurrent.TimeUnit;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;

/**
 * @author Zhujialiang
 */
@Slf4j
@Service
public class RedisLockServiceImpl implements RedisLockService {

    private static final int DEFAULT_SINGLE_EXPIRE_TIME = 5;

    @Autowired
    @Qualifier("redisDefineTemplate")
    private RedisTemplate<String, String> redisTemplateDistribution;

    @Override
    public boolean tryLock(String key) {
        return tryLock(key, 0L, null);
    }

    @SuppressWarnings("ConstantConditions")
    @Override
    public boolean tryLock(final String key, final long timeout, final TimeUnit unit) {
        return redisTemplateDistribution.execute((RedisCallback<Boolean>) instance -> {
            long nano = System.nanoTime();
            do {
                log.info("try lock key: " + key);
                Boolean i = instance.setNX(key.getBytes(), key.getBytes());
                if (i) {
                    instance.expire(key.getBytes(), DEFAULT_SINGLE_EXPIRE_TIME);
                    log.info("get lock, key: " + key + " , expire in " + DEFAULT_SINGLE_EXPIRE_TIME + " seconds.");
                    return Boolean.TRUE;
                } else {
                    byte[] desc = instance.get(key.getBytes());
                    log.info("key: " + key + " locked by another business：" + Arrays.toString(desc));
                }
                if (timeout == 0) {
                    break;
                }
                try {
                    Thread.sleep(300);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            } while ((System.nanoTime() - nano) < unit.toNanos(timeout));
            return Boolean.FALSE;
        });
    }

    @Override
    public void unLock(String key) {
            try {
                redisTemplateDistribution.delete(key);
                log.debug("release lock, key :" + key);
            } catch (Exception e) {
                log.error(e.getMessage(), e);
            }
    }

    /**
     * 加锁
     * @param key
     * @param value 当前线程操作时的 System.currentTimeMillis() + 2000，2000是超时时间，这个地方不需要去设置redis的expire，
     *              也不需要超时后手动去删除该key，因为会存在并发的线程都会去删除，造成上一个锁失效，结果都获得锁去执行
     * @return
     */
    @Override
    public boolean lock(String key, String value) {
        //如果key值不存在，则返回 true，且设置 value
        if (redisTemplateDistribution.opsForValue().setIfAbsent(key, value)) {
            return true;
        }
        //获取key的值，判断是是否超时
        String curVal = (String) redisTemplateDistribution.opsForValue().get(key);
        if (StringUtils.isNotEmpty(curVal) && Long.parseLong(curVal) < System.currentTimeMillis()) {
            //获得之前的key值，同时设置当前的传入的value。这个地方可能几个线程同时过来，但是redis本身天然是单线程的，所以getAndSet方法还是会安全执行，
            //首先执行的线程，此时curVal当然和oldVal值相等，因为就是同一个值，之后该线程set了自己的value，后面的线程就取不到锁了
            String oldVal = (String) redisTemplateDistribution.opsForValue().getAndSet(key, value);
            if(StringUtils.isNotEmpty(oldVal) && oldVal.equals(curVal)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public void unlock(String key, String value) {
        try {
            String curVal = redisTemplateDistribution.opsForValue().get(key);
            if (StringUtils.isNotEmpty(curVal) && curVal.equals(value)) {
                redisTemplateDistribution.opsForValue().getOperations().delete(key);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

