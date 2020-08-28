package com.mine.redis.util;

import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ZSetOperations;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

/**
 * @author chenhaiyu
 * @Description
 * @version V1.0
 * @Date 2019年10月18日 20:11:00
 * @Copyright Copyright © 2019 深圳花儿绽放网络科技股份有限公司. All rights reserved.
 */
@Component
public class RedisTemplateUtils {
    @Autowired
    @Qualifier("redisDefineTemplate")
    private RedisTemplate<String, String> redisTemplate;
    
    /**
     * 判断 key 是否存在
     * @param key
     * @return
     */
    public boolean exists(String key) {
        return redisTemplate.hasKey(key);
    }
    
    /**
     * 实现命令：expire 设置过期时间，单位秒
     * 
     * @param key 键 
     * @param timeout 过期时间
     * @return
     */
    public void expire(String key, long timeout) {
        redisTemplate.expire(key, timeout, TimeUnit.SECONDS);
    }

    /**
     * 实现命令：expire 设置过期时间，单位毫秒
     *
     * @param key 键
     * @param timeout 过期时间
     * @return
     */
    public void pexpire(String key, long timeout) {
        redisTemplate.expire(key, timeout, TimeUnit.MILLISECONDS);
    }
    
    /**
     * 获取 key 的过期时间
     * @param key 键 不能为null
     * @return 时间(秒) 返回0代表为永久有效 
     */
    public long getExpire(String key) {
        return redisTemplate.getExpire(key);
    }
    
    /**
     * 删除
     * @param key
     */
    @SuppressWarnings("unchecked")
    public boolean del(String... key) {
        if (key != null && key.length > 0) {
            if (key.length == 1) {
                return redisTemplate.delete(key[0]);
            } else {
                Long result = redisTemplate.delete(CollectionUtils.arrayToList(key));
                if (result > 0) {
                    return true;
                }
            }
        }
        return false;
    }
    
    // ----------------------------- String -----------------------------
    
    /**
     * 获取 key的值
     * @param key 键
     * @return value 值
     */
    public String get(String key) {
        return key==null?null:redisTemplate.opsForValue().get(key);
    }
    
    /**
     * 存储设置，无过期时间
     * @param key 键
     * @param value 值
     */
    public void set(String key, String value) {
        redisTemplate.opsForValue().set(key, value);
    }
    
    /**
     * 存储设置，带过期时间
     * @param key 键
     * @param value 值
     * @param timeout 过期时间
     */
    public void setEx(String key, String value, long timeout) {
        redisTemplate.opsForValue().set(key, value, timeout, TimeUnit.SECONDS);
    }
    
    /**
     * 实现命令：INCR key，增加key一次
     * 
     * @param key 键
     * @param delta 变化因子
     * @return
     */
    public long incr(String key, long delta) {
        return redisTemplate.opsForValue().increment(key, delta);
    }
    
    // ----------------------------- Map -----------------------------
    /**
     * 设置Map的值
     * @param key 键
     * @param field 项
     * @param value 值
     * @return
     */
    public void hset(String key , String field, String value) {
        redisTemplate.opsForHash().put(key, field, value);
    }
    
    /**
     * 获取hashKey对应的所有键值
     * @param key 键
     * @param field 域
     * @return 获取的对象
     */
    public Object hget(String key, String field){
        return redisTemplate.opsForHash().get(key, field);
    }
    
    /**
     * 获取hashKey对应的所有键值
     * @param key 键
     * @return 对应的多个键值
     */
    public Map<Object,Object> hmget(String key){
        return redisTemplate.opsForHash().entries(key);
    }
    
    /**
     * HashSet
     * @param key 键
     * @param map 对应多个键值
     * @return true 成功 false 失败
     */
    public boolean hmset(String key, Map<Object,Object> map){
        try {
            redisTemplate.opsForHash().putAll(key, map);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
    
    /**
     * 获取hashKey对应的所有键值
     * @param key 键
     * @return 获取的对象
     */
    public Set<Object> hkeys(String key){
        return redisTemplate.opsForHash().keys(key);
    }

    /**
     * 获取哈希表中字段的数量
     * @param key 键
     * @return 获取的对象
     */
    public long hlen(String key){
        return redisTemplate.opsForHash().size(key);
    }

    /**
     * 获取hashKey对应的所有键值对
     * @param key 键
     * @return 获取的对象
     */
    public Map<Object, Object> hgetAll(String key){
        return redisTemplate.opsForHash().entries(key);
    }
    
    /**
     * 判断是否存在
     * @param key
     * @param field
     * @return
     */
    public boolean hexists(String key , String field) {
        return redisTemplate.opsForHash().hasKey(key, field);
    }
    
    /**
     * 删除hasmMap的所有field项
     * @param key
     * @param field
     * @return
     */
    public void hdel(String key , Object... field) {
        redisTemplate.opsForHash().delete(key, field);
    }
    
    /**
     * HashMap的域值的变化
     * @param key
     * @param field
     * @param delta
     * @return
     */
    public double hincr(String key, String field, double delta) {
        return redisTemplate.opsForHash().increment(key, field, delta);
    }
    
    
    // ----------------------------- List -----------------------------

    /**
     * 将一个或多个值插入到列表头部
     * @param key
     * @param value
     * @return
     */
    public double lpush(String key, String value) {
        return redisTemplate.opsForList().leftPush(key, value);
    }
    /**
     * 从列表头部获取值并移除
     * @param key
     * @return
     */
    public String lpop(String key) {
        return redisTemplate.opsForList().leftPop(key);
    }


    // ----------------------------- Set -----------------------------
    /** 
     * 添加set 元素
     * @param key 
     * @param value 
     */  
    public void sadd(String key, String... value) {  
        redisTemplate.opsForSet().add(key, value);
    }
    
    /**
     * 统计key下的元素数量
     * @param key
     * @return
     */
    public Long scard(String key) {
        return redisTemplate.opsForSet().size(key);
    }

    public Set<String> members(String key) {
        return redisTemplate.opsForSet().members(key);
    }

    public boolean isMember(String key, String value) {
        return redisTemplate.opsForSet().isMember(key, value);
    }
    
    /**
     * 删除Set中的value值
     * @param key 键
     * @param value 值
     * @return
     */
    public Long srem(String key, String value) {
        return redisTemplate.opsForSet().remove(key, value);
    }
    // ----------------------------- ZSet -----------------------------
    /**
     * 查询value对应的score   zscore
     *
     * @param key
     * @param value
     * @return
     */
    public Double zscore(String key, String value) {
        return redisTemplate.opsForZSet().score(key, value);
    }
    
    /**
     * 查询sort set的列表  zrange
     *
     * @param key
     * @return
     */
    public Set<String> zrange(String key, Integer offset, Integer limit) {
        return redisTemplate.opsForZSet().range(key, offset, offset + limit);
    }

    /**
     * 查询sort set的列表  zrange
     *
     * @param key
     * @return
     */
    public Set<String> zrevrange(String key, Integer offset, Integer limit) {
        return redisTemplate.opsForZSet().reverseRange(key, offset, offset + limit);
    }

    /**
     * 查询sort set的列表  zrange
     *
     * @param key
     * @return
     */
    public Set<ZSetOperations.TypedTuple<String>> zrevrangeWithScores(String key, Integer offset, Integer limit) {
        return redisTemplate.opsForZSet().reverseRangeWithScores(key, offset, limit);
    }

    /**
     * 查询成员排名  zrank
     *
     * @param key
     * @param member
     * @return
     */
    public Long zrank(String key, String member) {
        return redisTemplate.opsForZSet().rank(key, member);
    }
    
    /**
     * 获取成员数量
     *
     * @param key
     * @return
     */
    public Long zcard(String key) {
        return redisTemplate.opsForZSet().size(key);
    }

    /**
     * 根据分数删除成员
     *
     * @param key
     * @return
     */
    public Long zremrangeByScore(String key, double min, double max) {
        return redisTemplate.opsForZSet().removeRangeByScore(key, min, max);
    }
    
    /**
     * score的增加or减少 zincrby
     *
     * @param key
     * @param value
     * @param score
     */
    public Double zincr(String key, String value, double score) {
        return redisTemplate.opsForZSet().incrementScore(key, value, score);
    }
    
    /**
     * 设置set的值
     * @param key
     * @param value
     * @param score
     */
    public void zadd(String key, String value, double score) {
        redisTemplate.opsForZSet().add(key, value, score);
    }
    
    /**
     * 删除set的值
     * @param key
     * @param value
     */
    public Long zrem(String key, String value) {
        return redisTemplate.opsForZSet().remove(key, value);
    }
    // ----------------------------- List -----------------------------
}
