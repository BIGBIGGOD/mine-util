package com.mine.redis;

import java.io.Serializable;
import java.util.concurrent.Callable;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.Cache;
import org.springframework.cache.support.SimpleValueWrapper;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import com.mine.util.javaSerializeUtil.JavaSerializeUtil;

/**
 * 缓存注解使用
 *
 * @author admin
 */
@Repository
public class RedisCache implements Cache {

    private static final Logger LOGGER = LoggerFactory.getLogger(RedisCache.class);

    /**
     * 过期时间
     */
    private static final int TIME_OUT = 60 * 60 * 24;

    /**
     * redis模板
     */
    private RedisTemplate<String, Object> redisTemplate;

    /**
     * 默认key
     */
    private String name;

    /**
     * 超时时间
     */
    private int timeout;

    @Override
    public Object getNativeCache() {
        return this.redisTemplate;
    }

    @Override
    public ValueWrapper get(Object key) {
        LOGGER.info("redis get cache key={}", key);
        final String keyInfo = key.toString();
        Object object;
        object = redisTemplate.execute((RedisCallback<Object>) connection -> {
            try {
                byte[] key1 = keyInfo.getBytes();
                byte[] value = connection.get(key1);
                if (value == null) {
                    return null;
                }
                return toObject(value);
            } catch (Exception e) {
                LOGGER.error("获取缓存失败", e);
            }
            return null;
        });
        return (object != null ? new SimpleValueWrapper(object) : null);
    }

    @Override
    public void put(final Object key, final Object value) {
        LOGGER.info("redis put cache key={}", key);
        final String keyf = key.toString();
        redisTemplate.execute((RedisCallback<Boolean>) connection -> {
            try {
                byte[] keyb = keyf.getBytes();
                byte[] valueb = toByteArray(value);
                if (valueb == null) {
                    return false;
                }
                connection.set(keyb, valueb);
                if (TIME_OUT > 0) {
                    connection.expire(keyb, TIME_OUT);
                }
                return true;
            } catch (Exception e) {
                LOGGER.error("写入缓存失败,key={},value={}", key, value, e);
            }
            return false;
        });
    }

    @Override
    public void evict(Object key) {
        LOGGER.info("redis delete cache key={}", key);
        final String keyInfo = key.toString();
        redisTemplate.execute((RedisCallback<Long>) connection -> connection.del(keyInfo.getBytes()));
    }

    @Override
    public void clear() {
    }

    @Override
    public <T> T get(final Object key, Class<T> type) {
        if (StringUtils.isEmpty(key) || null == type) {
            return null;
        } else {
            final String finalKey;
            if (key instanceof String) {
                finalKey = (String) key;
            } else {
                finalKey = key.toString();
            }
            final Object object = redisTemplate.execute((RedisCallback<Object>) connection -> {
                try {
                    byte[] key1 = finalKey.getBytes();
                    byte[] value = connection.get(key1);
                    if (value == null) {
                        return null;
                    }
                    return toObject(value);
                } catch (Exception e) {
                    LOGGER.error("获取缓存异常,key={}", key, e);
                }
                return null;
            });
            if (type.isInstance(object)) {
                return (T) object;
            } else {
                return null;
            }
        }
    }

    @Override
    public <T> T get(Object o, Callable<T> callable) {
        return null;
    }

    @Override
    public ValueWrapper putIfAbsent(Object key, Object value) {
        ValueWrapper valueWrapper = get(key);
        if (valueWrapper == null) {
            put(key, value);
        }
        return get(key);
    }


    public RedisTemplate<String, Object> getRedisTemplate() {
        return redisTemplate;
    }

    public void setRedisTemplate(RedisTemplate<String, Object> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String getName() {
        return this.name;
    }

    public int getTimeout() {
        return timeout;
    }

    public void setTimeout(int timeout) {
        this.timeout = timeout;
    }

    private byte[] toByteArray(Object obj) throws Exception {
        if (obj == null) {
            return null;
        }
        if (obj instanceof Serializable) {
            Serializable serializableObj = (Serializable) obj;
            return JavaSerializeUtil.serialize(serializableObj);
        }
        throw new Exception("没有实现序列化接口");
    }

    private Object toObject(byte[] bytes) throws Exception {
        return JavaSerializeUtil.unserialize(bytes);
    }
}