package com.mine.redis.util;

import java.time.Duration;

import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.*;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.jedis.JedisClientConfiguration;
import org.springframework.data.redis.connection.jedis.JedisClientConfiguration.DefaultJedisClientConfigurationBuilder;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import com.alibaba.fastjson.support.spring.GenericFastJsonRedisSerializer;

/**
 * @author jqd
 * @Description 根据properties文件加载redis配置，并构造redis的相关bean
 * @version V1.0
 * @Date 2019年10月15日 14:54:59
 * @Copyright Copyright © 2019 深圳花儿绽放网络科技股份有限公司. All rights reserved.
 */

@Configuration
@PropertySource(value = { "classpath:redis.properties" })
public class StandaloneRedisConfig {
    @Value("${redis.timeout}")
    private String timeout;
    
    @Bean(name = "redisPoolDistribution")
    @ConfigurationProperties(prefix = "redis")
    @Scope(value = "prototype")
    public GenericObjectPoolConfig redisPoolDistribution() {
        return new GenericObjectPoolConfig();
    }

    @Bean(name = "standaloneConfigurationIag")
    @ConfigurationProperties(prefix = "redis")
    public RedisStandaloneConfiguration standaloneConfigurationIag() {
        return new RedisStandaloneConfiguration();
    }

    @Bean(name = "standaloneFactoryDistribution")
    @Primary
    public JedisConnectionFactory standaloneFactoryDistribution() {
        DefaultJedisClientConfigurationBuilder builder = (DefaultJedisClientConfigurationBuilder)JedisClientConfiguration.builder();
        builder.usePooling();
        builder.readTimeout(Duration.ofMillis(Long.valueOf(timeout)));
        builder.connectTimeout(Duration.ofMillis(Long.valueOf(timeout)));
        builder.poolConfig(redisPoolDistribution());
        JedisClientConfiguration poolConfig = builder.build();
        return new JedisConnectionFactory(standaloneConfigurationIag(), poolConfig);
    }

    @Bean(name = "redisTemplate")
    @Primary
    public RedisTemplate redisTemplateDistribution() {
        RedisTemplate<Object, Object> template = getRedisTemplate();
        template.setConnectionFactory(standaloneFactoryDistribution());
        System.out.println("构造redis的bean完成");
        return template;
    }


    private RedisTemplate<Object, Object> getRedisTemplate() {
        RedisTemplate<Object, Object> template = new RedisTemplate<>();
        // 设置String 的 key 和 value序列化模式
        template.setKeySerializer(new StringRedisSerializer());
        template.setValueSerializer(new StringRedisSerializer());
        // 设置hash key 和value序列化模式
        template.setHashKeySerializer(new StringRedisSerializer());
        template.setHashValueSerializer(new GenericFastJsonRedisSerializer());
        //关闭事务支持
        template.setEnableTransactionSupport(false);
        return template;
    }
}
