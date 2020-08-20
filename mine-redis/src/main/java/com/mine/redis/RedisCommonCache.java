package com.mine.redis;

import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import lombok.extern.slf4j.Slf4j;
import redis.clients.jedis.Jedis;

/**
 * 基础redis操作
 * @author admin
 */
@Slf4j
@Repository
public class RedisCommonCache {

    @Autowired
    @Qualifier("coreRedisExecutor")
    private RedisExecutor redisExecutor;

    /**
     * 设置超时
     *
     * @param key
     * @param timeOut
     * @return
     */
    public boolean expire(final String key, final int timeOut) {
        JedisCallable<Boolean> callable = new JedisCallable<Boolean>() {
            @Override
            public Boolean call(Jedis instance) throws Exception {
                instance.expire(key, timeOut);
                return true;
            }
        };
        return redisExecutor.doInRedis(callable);
    }

    /**
     * key value设置
     *
     * @param key
     * @param value
     * @return
     */
    public Boolean set(final String key, final String value) {
        JedisCallable<Boolean> callable = new JedisCallable<Boolean>() {
            @Override
            public Boolean call(Jedis instance) throws Exception {
                instance.set(key, value);
                return true;
            }
        };
        return redisExecutor.doInRedis(callable);
    }

    /**
     * 获取key的值
     *
     * @param key
     * @return
     */
    public String get(final String key) {
        JedisCallable<String> callable = new JedisCallable<String>() {
            @Override
            public String call(Jedis instance) throws Exception {
                return instance.get(key);
            }
        };
        return redisExecutor.doInRedis(callable);
    }

    /**
     * 获取整型
     *
     * @param key
     * @param defaultValue
     * @return
     */
    public int getInteger(final String key, int defaultValue) {
        return parseInt(get(key), defaultValue);
    }

    /**
     * 获取浮点型
     *
     * @param key
     * @param defaultValue
     * @return
     */
    public double getDouble(final String key, double defaultValue) {
        return parseDouble(get(key), defaultValue);
    }


    /**
     * 添加数量
     *
     * @param key
     * @param count
     * @return
     */
    public int incrBy(final String key, final int count) {
        JedisCallable<Integer> callable = new JedisCallable<Integer>() {
            @Override
            public Integer call(Jedis instance) throws Exception {
                return instance.incrBy(key, count).intValue();
            }
        };
        return redisExecutor.doInRedis(callable);
    }

    /**
     * 添加数量
     *
     * @param key
     * @param count
     * @return
     */
    public double incrBy(final String key, final double count) {
        JedisCallable<Double> callable = new JedisCallable<Double>() {
            @Override
            public Double call(Jedis instance) throws Exception {
                return instance.incrByFloat(key, count).doubleValue();
            }
        };
        return redisExecutor.doInRedis(callable);
    }

    /**
     * key value设置
     *
     * @param key
     * @param value
     * @return
     */
    public Boolean hset(final String key, final String subKey, final String value) {
        JedisCallable<Boolean> callable = new JedisCallable<Boolean>() {
            @Override
            public Boolean call(Jedis instance) throws Exception {

                instance.hset(key, subKey, value);
                return true;
            }
        };
        return redisExecutor.doInRedis(callable);
    }

    public int hgetInteger(String key, String subKey, int defaultValue) {
        return parseInt(hget(key, subKey), defaultValue);
    }

    public double hgetDouble(String key, String subKey, double defaultValue) {
        return parseDouble(hget(key, subKey), defaultValue);
    }

    public long hgetLong(String key, String subKey, long defaultValue) {
        return parseLong(hget(key, subKey), defaultValue);
    }


    /**
     * lpush
     * @param key
     * @param value
     * @return
     */
    public Boolean lpush(final String key, final String value) {
        JedisCallable<Boolean> callable = new JedisCallable<Boolean>() {
            @Override
            public Boolean call(Jedis instance) throws Exception {
                instance.lpush(key,value);
                return true;
            }
        };
        return redisExecutor.doInRedis(callable);
    }

    /**
     * rpop
     * @param key
     * @return
     */
    public String rpop(final String key) {
        JedisCallable<String> callable = new JedisCallable<String>() {
            @Override
            public String call(Jedis instance) throws Exception {
                return instance.rpop(key);
            }
        };
        return redisExecutor.doInRedis(callable);
    }

    public String brpop(final String key) {
        JedisCallable<String> callable = new JedisCallable<String>() {
            @Override
            public String call(Jedis instance) throws Exception {
                List<String> list = instance.brpop(3, key);
                if (CollectionUtils.isEmpty(list)) {
                    return null;
                }
                return list.get(1);
            }
        };
        return redisExecutor.doInRedis(callable);
    }




    /**
     * 获取值
     *
     * @param key
     * @param subKey
     * @return
     */
    public String hget(final String key, final String subKey) {
        JedisCallable<String> callable = new JedisCallable<String>() {
            @Override
            public String call(Jedis instance) throws Exception {
                return instance.hget(key, subKey);
            }
        };
        return redisExecutor.doInRedis(callable);
    }

    /**
     * 获取所有值
     *
     * @param key
     * @return
     */
    public Map hgetAll(final String key) {
        JedisCallable<Map> callable = new JedisCallable<Map>() {
            @Override
            public Map call(Jedis instance) throws Exception {
                return instance.hgetAll(key);
            }
        };
        return redisExecutor.doInRedis(callable);
    }

    /**
     * 原子加
     *
     * @param key
     * @param subKey
     * @param count
     * @return
     */
    public int hincrBy(final String key, final String subKey, final int count) {
        JedisCallable<Integer> callable = new JedisCallable<Integer>() {
            @Override
            public Integer call(Jedis instance) throws Exception {
                return instance.hincrBy(key, subKey, count).intValue();
            }
        };
        return redisExecutor.doInRedis(callable);
    }

    /**
     * 原子加
     *
     * @param key
     * @param subKey
     * @param count
     * @return
     */
    public double hincrBy(final String key, final String subKey, final double count) {
        JedisCallable<Double> callable = new JedisCallable<Double>() {
            @Override
            public Double call(Jedis instance) throws Exception {
                return instance.hincrByFloat(key, subKey, count).doubleValue();
            }
        };
        return redisExecutor.doInRedis(callable);
    }

    /**
     * 添加Set集合
     *
     * @param key
     * @param value
     * @return
     */
    public Long sadd(final String key, final String value) {
        JedisCallable<Long> callable = new JedisCallable<Long>() {
            @Override
            public Long call(Jedis instance) throws Exception {
                return instance.sadd(key, value);
            }
        };
        return redisExecutor.doInRedis(callable);
    }

    /**
     * 删除
     *
     * @param key
     * @param value
     * @return
     */
    public Long sremove(final String key, final String value) {
        JedisCallable<Long> callable = new JedisCallable<Long>() {
            @Override
            public Long call(Jedis instance) throws Exception {
                return instance.srem(key, value);
            }
        };
        return redisExecutor.doInRedis(callable);
    }


    /**
     * 删除
     * @param key
     * @return
     */
    public Boolean delete(final String key) {
        JedisCallable<Boolean> callable = new JedisCallable<Boolean>() {
            @Override
            public Boolean call(Jedis instance) throws Exception {
                instance.del(key);
                return true;
            }
        };
        return redisExecutor.doInRedis(callable);
    }

    /**
     * 数量
     *
     * @param key
     * @return
     */
    public Long scount(final String key) {
        JedisCallable<Long> callable = new JedisCallable<Long>() {
            @Override
            public Long call(Jedis instance) throws Exception {
                return instance.scard(key);
            }
        };
        return redisExecutor.doInRedis(callable);
    }

    /**
     * 判断是否存在
     *
     * @param key
     * @param value
     * @return
     */
    public boolean sismember(final String key, final String value) {
        JedisCallable<Boolean> callable = new JedisCallable<Boolean>() {
            @Override
            public Boolean call(Jedis instance) throws Exception {
                return instance.sismember(key, value);
            }
        };
        return redisExecutor.doInRedis(callable);
    }

    /**
     * 查询集合
     *
     * @param key
     * @return
     */
    public Set<String> smembers(final String key) {
        JedisCallable<Set<String>> callable = new JedisCallable<Set<String>>() {
            @Override
            public Set<String> call(Jedis instance) throws Exception {
                return instance.smembers(key);
            }
        };
        return redisExecutor.doInRedis(callable);
    }

    /**
     * 格式转换
     *
     * @param temp
     * @param defaultValue
     * @return
     */
    private int parseInt(String temp, int defaultValue) {
        try {
            if (StringUtils.isBlank(temp)) {
                return defaultValue;
            }
            return Integer.parseInt(temp);
        } catch (Exception e) {
            log.error("格式转换错误,temp={},defaultValue={}", temp, defaultValue);
        }
        return defaultValue;
    }

    private long parseLong(String temp, long defaultValue) {
        try {
            if (StringUtils.isBlank(temp)) {
                return defaultValue;
            }
            return Long.parseLong(temp);
        } catch (Exception e) {
            log.error("格式转换错误,temp={},defaultValue={}", temp, defaultValue);
        }
        return defaultValue;
    }

    /**
     * 格式转换
     *
     * @param temp
     * @param defaultValue
     * @return
     */
    private double parseDouble(String temp, double defaultValue) {
        try {
            if (StringUtils.isBlank(temp)) {
                return defaultValue;
            }
            return Double.parseDouble(temp);
        } catch (Exception e) {
            log.error("格式转换错误,temp={},defaultValue={}", temp, defaultValue);
        }
        return defaultValue;
    }


}
