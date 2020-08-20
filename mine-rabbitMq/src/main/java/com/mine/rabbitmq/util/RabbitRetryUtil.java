package com.mine.rabbitmq.util;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.BuiltinExchangeType;
import com.rabbitmq.client.Channel;
import lombok.extern.slf4j.Slf4j;

/**
 * @author jiangqingdong
 * @version v1.0
 * @Description
 * @DATE 2020/6/10 0010 16:31
 * @Copyright Copyright © 2019 深圳花儿绽放网络科技股份有限公司. All rights reserved.
 */
@Component
@Slf4j
public class RabbitRetryUtil {

    /**
     * 消息订阅初始化
     *
     * @param queueName  订阅的队列名称
     * @param routingKey 订阅的路由规则
     * @throws IOException
     */
    public Channel init(String exchangeName, Channel channel, String queueName, String routingKey) throws IOException {
        // 声明Exchange：主体，重试，失败
        channel.exchangeDeclare(exchangeName, BuiltinExchangeType.TOPIC, false);
        channel.exchangeDeclare(exchangeName + "@retry", BuiltinExchangeType.TOPIC, false);
        channel.exchangeDeclare(exchangeName + "@failed", BuiltinExchangeType.TOPIC, false);

        // 声明监听队列
        Map<String, Object> arguments = new HashMap<>();
        arguments.put("x-dead-letter-exchange", exchangeName);
        arguments.put("x-message-ttl", 30 * 1000);
        arguments.put("x-dead-letter-routing-key", queueName);
        channel.queueDeclare(queueName + "@retry", false, false, false, arguments);

        channel.queueDeclare(queueName, false, false, false, null);
        channel.queueDeclare(queueName + "@failed", false, false, false, null);

        // 绑定监听队列到Exchange
        channel.queueBind(queueName + "@retry", exchangeName + "@retry", routingKey);
        channel.queueBind(queueName, exchangeName, routingKey);
        channel.queueBind(queueName + "@failed", exchangeName + "@failed", routingKey);

        return channel;
    }

    /**
     * 获取消息重试次数
     *
     * @param properties AMQP消息属性
     * @return 消息重试次数
     */
    public Long getRetryCount(AMQP.BasicProperties properties) {
        Long retryCount = 1L;
        try {
            Map<String, Object> headers = properties.getHeaders();
            if (headers != null) {
                if (headers.containsKey("handle-count")) {
                    retryCount = (Long) headers.get("handle-count");
                }
            }
        } catch (Exception e) {
            log.error("获取重试次数出错，e", e);
        }
        return retryCount;
    }

    /**
     * 获取原始的routingKey
     *
     * @param properties   AMQP消息属性
     * @param defaultValue 默认值
     * @return 原始的routing-key
     */
    public String getOrigRoutingKey(AMQP.BasicProperties properties, String defaultValue) {
        String routingKey = defaultValue;
        try {
            Map<String, Object> headers = properties.getHeaders();
            if (headers != null) {
                if (headers.containsKey("routing-key")) {
                    routingKey = headers.get("routing-key").toString();
                }
            }
        } catch (Exception e) {
            log.error("替换headers中的routing-key出错,e", e);
        }
        return routingKey;
    }

    /**
     * 从已有的properties中创建新的properties，使用提供的headers字段覆盖已有的headers
     *
     * @param properties AMQP属性
     * @param headers    要覆盖的headers
     * @return 新创建的properties
     */
    public AMQP.BasicProperties createOverrideProperties(AMQP.BasicProperties properties, Map<String, Object> headers) {
        return new AMQP.BasicProperties(
                properties.getContentType(),
                properties.getContentEncoding(),
                headers,
                properties.getDeliveryMode(),
                properties.getPriority(),
                properties.getCorrelationId(),
                properties.getReplyTo(),
                properties.getExpiration(),
                properties.getMessageId(),
                properties.getTimestamp(),
                properties.getType(),
                properties.getUserId(),
                properties.getAppId(),
                properties.getClusterId()
        );
    }
}
