package com.mine.util.rabbitmqutil.consumer;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.mine.util.rabbitmqutil.util.RabbitRetryUtil;
import com.rabbitmq.client.*;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

/**
 * @author jiangqingdong
 * @version v1.0
 * @Description
 * @DATE 2020/6/4 0004 21:13
 * @Copyright Copyright © 2019 深圳花儿绽放网络科技股份有限公司. All rights reserved.
 */
@Slf4j
@Component
public class RabbitConsumer {
    @Autowired
    private RabbitRetryUtil rabbitRetryUtil;

    /**
     * 使用默认路由器，由队列名替代路由键，无需队列绑定路由器，多个消费者存在时存在竞争关系
     *
     * @param channel
     * @param queueName
     * @throws IOException
     */
    public void generalConsumer(Channel channel, String queueName) throws IOException {
        //定义队列名称
        if (StringUtils.isBlank(queueName)) {
            queueName = "general_queue_name";
        }
        //声明要消费的队列,方法属性注释在生产者中有笔记
        channel.queueDeclare(queueName, false, false, false, null);

        //注意使用默认路由器的时候，在消费者端不需要将队列和路由器通过路由键绑定起来，此时路由键等同于队列名
        //程序会使用默认路由器并且以队列名作为路由键进行匹配

        //是否自动确认删除队列中已经消费了的此条消息，默认true
        boolean autoAck = true;
        //定义消费者，即谁接收消息，并重写消费消息方法
        Consumer consumer = new DefaultConsumer(channel) {
            @Override
            public void handleDelivery(String consumerTag, //该消费者的标签
                                       Envelope envelope,//字面意思为信封：packaging data for the message
                                       AMQP.BasicProperties properties, //消息的基本属性，message content header data
                                       byte[] body) //message body
            {
                String message = new String(body, StandardCharsets.UTF_8);
                log.info("generalConsumer-->routingKey={}-->message={}", envelope.getRoutingKey(), message);
            }
        };

        while (true) {
            channel.basicConsume(queueName, autoAck, consumer);
        }
    }

    /**
     * fanout类型路由器，默认不使用路由键，会向该路由器内所有队列广播消息，即所有队列都会收到同一消息，不存在竞争关系
     *
     * @param channel
     * @throws IOException
     */
    public void fanoutConsumer(Channel channel) throws IOException {

        //声明一个随机名字的队列并返回队列名,这样子的队列会在未使用后立即删除（是所有消息使用后还是程序停止后？）
        String queueName = channel.queueDeclare().getQueue();

        //路由器的名字
        String exchangeName = "fanout_exchange_name";

        //声明fanout类型的路由器
        channel.exchangeDeclare(exchangeName, BuiltinExchangeType.FANOUT);

        //路由键，即绑定键，因为这里使用的路由器是fanout类型，所以路由键是空字符串
        String routingKey = "";

        //其他绑定参数,下面使用的队列绑定路由器没有用到该参数
        Map<String, Object> arguments = null;

        //绑定队列到路由器上，将路由器、路由键、队列名关联起来
        channel.queueBind(queueName, exchangeName, routingKey, arguments);

        //开始监听消息
        Consumer consumer = new DefaultConsumer(channel) {
            @Override
            public void handleDelivery(String consumerTag,
                                       Envelope envelope,
                                       AMQP.BasicProperties properties,
                                       byte[] body) {
                String message = new String(body, StandardCharsets.UTF_8);
                log.info("fanoutConsumer-->message={}", message);
            }
        };
        while (true) {
            channel.basicConsume(queueName, true, consumer);
        }
    }

    /**
     * direct类型路由器，将队列与路由器、路由键绑定进行严格消息派发，但是该路由键可以绑定多个队列，此时会向该路由器下指定路由键的所有队列进行消息广播，不存在竞争关系
     *
     * @param channel
     * @param queueName
     * @throws IOException
     */
    public void directConsumer(Channel channel, String queueName) throws IOException {

        //路由器的名字
        String exchangeName = "direct_exchange_name";

        //声明direct类型的路由器
        channel.exchangeDeclare(exchangeName, BuiltinExchangeType.DIRECT);

        //定义队列名称
        if (StringUtils.isBlank(queueName)) {
            queueName = "direct_queue_name";
        }

        //声明要消费的队列,方法属性注释在生产者中有笔记
        channel.queueDeclare(queueName, false, false, false, null);

        //路由键，即绑定键
        String routingKey = "directRoutingKey";

        //其他绑定参数,下面使用的队列绑定路由器没有用到该参数
        Map<String, Object> arguments = null;

        //绑定队列到路由器上，将路由器、路由键、队列名关联起来，
        // 因为路由器类型是direct类型，所以当使用不同的队列和相同的路由键去绑定路由器时，该类型路由器会多重绑定，路由器会向绑定的队列中广播消息是的，同一消息会在不同队列中都会存在
        channel.queueBind(queueName, exchangeName, routingKey, arguments);

        //开始监听消息
        Consumer consumer = new DefaultConsumer(channel) {
            @Override
            public void handleDelivery(String consumerTag,
                                       Envelope envelope,
                                       AMQP.BasicProperties properties,
                                       byte[] body) {
                String message = new String(body, StandardCharsets.UTF_8);
                log.info("directConsumer-->message={}", message);
            }
        };
        while (true) {
            channel.basicConsume(queueName, true, consumer);
        }
    }

    /**
     * topic类型路由器，模糊匹配路由键进行消息派发，相当于增强版direct类型路由器，消息在可接收方是全体广播，不存在消息竞争
     *
     * @param channel
     * @param queueName
     * @throws IOException
     */
    public void topicConsumer(Channel channel, String queueName) throws IOException {

        //路由器的名字
        String exchangeName = "topic_exchange_name";

        //声明topic类型的路由器
        channel.exchangeDeclare(exchangeName, BuiltinExchangeType.TOPIC, false);

        //定义队列名称
        if (StringUtils.isBlank(queueName)) {
            queueName = "topic_queue_name";
        }
        //声明要消费的队列,方法属性注释在生产者中有笔记
        channel.queueDeclare(queueName, false, false, false, null);

        //其他绑定参数,下面使用的队列绑定路由器没有用到该参数
        Map<String, Object> arguments = null;

        //路由键，即绑定键，在topic类型的路由器下，可以采用模糊匹配，如:test.*,这样就是收到所有所有路由键为test开头且后面只带一个单词的消息
        String[] routingKeys = {"product.*", "*.info", "xxx"};

        // 因为路由器类型是topic类型，所以可以把这个队列与路由器之间关联多个路由键，从而接受多种消息
        for (String routingKey : routingKeys) {
            //绑定队列到路由器上，将路由器、路由键、队列名关联起来，
            channel.queueBind(queueName, exchangeName, routingKey);
        }

        //开始监听消息
        Consumer consumer = new DefaultConsumer(channel) {
            @Override
            public void handleDelivery(String consumerTag,
                                       Envelope envelope,
                                       AMQP.BasicProperties properties,
                                       byte[] body) {
                String message = new String(body, StandardCharsets.UTF_8);
                log.info("topicConsumer-->routingKey={}-->message={}", envelope.getRoutingKey(), message);
            }
        };
        while (true) {
            channel.basicConsume(queueName, true, consumer);
        }
    }

    /**
     * 消息接收、重试、转发队列
     *
     * @param channel
     * @param queueName
     * @throws IOException
     */
    public void topicRetryConsumer(Channel channel, String queueName, String routingKey) throws IOException {
        //路由器的名字
        String exchangeName = "topic_exchange_name";

        //初始化
        rabbitRetryUtil.init(exchangeName, channel, queueName, routingKey);

        //开始监听消息
        Consumer consumer = new DefaultConsumer(channel) {
            @Override
            public void handleDelivery(String consumerTag,
                                       Envelope envelope,
                                       AMQP.BasicProperties properties,
                                       byte[] body) throws IOException {
                try {
                    String message = new String(body, StandardCharsets.UTF_8);
                    log.info("topicConsumer-->exchange={}-->routingKey={}-->message={}", envelope.getExchange(), envelope.getRoutingKey(), message);
                    //当报错时会抛出异常进入重试机制
                    int i = 1 / 0;
                } catch (Exception e) {
                    long handleCount = rabbitRetryUtil.getRetryCount(properties);
                    if (handleCount > 3) {
                        log.info("重试次数大于3次自动加入到失败队列, handleCount={}", handleCount);
                        Map<String, Object> headers = properties.getHeaders();
                        if (headers == null) {
                            headers = new HashMap<>();
                        }
                        headers.put("routing-key", rabbitRetryUtil.getOrigRoutingKey(properties, envelope.getRoutingKey()));
                        channel.basicPublish(exchangeName + "@failed", routingKey, rabbitRetryUtil.createOverrideProperties(properties, headers), body);
                    } else {
                        log.info("重试次数小于等于3，则加入到重试队列，30s后再重试, handleCount={}", handleCount);
                        Map<String, Object> headers = properties.getHeaders();
                        if (headers == null) {
                            headers = new HashMap<>();
                        }
                        headers.put("handle-count", ++handleCount);
                        headers.put("routing-key", rabbitRetryUtil.getOrigRoutingKey(properties, envelope.getRoutingKey()));
                        channel.basicPublish(exchangeName + "@retry", routingKey, rabbitRetryUtil.createOverrideProperties(properties, headers), body);
                    }
                }
            }
        };
        while (true) {
            channel.basicConsume(queueName, true, consumer);
            channel.basicConsume(queueName + "@retry", true, consumer);
        }
    }

    /**
     * 消息限流设置
     *
     * @param channel
     * @param queueName
     * @throws IOException
     */
    public void topicLimitConsumer(Channel channel, String queueName) throws IOException {

        //路由器的名字
        String exchangeName = "topic_exchange_name";

        //声明topic类型的路由器
        channel.exchangeDeclare(exchangeName, BuiltinExchangeType.TOPIC, false);

        //定义队列名称
        if (StringUtils.isBlank(queueName)) {
            queueName = "topic_queue_name";
        }
        //声明要消费的队列,方法属性注释在生产者中有笔记
        channel.queueDeclare(queueName, false, false, false, null);

        //其他绑定参数,下面使用的队列绑定路由器没有用到该参数
        Map<String, Object> arguments = null;

        //路由键，即绑定键，在topic类型的路由器下，可以采用模糊匹配，如:test.*,这样就是收到所有所有路由键为test开头且后面只带一个单词的消息
        String[] routingKeys = {"product.*", "*.info", "xxx"};

        // 因为路由器类型是topic类型，所以可以把这个队列与路由器之间关联多个路由键，从而接受多种消息
        for (String routingKey : routingKeys) {
            //绑定队列到路由器上，将路由器、路由键、队列名关联起来，
            channel.queueBind(queueName, exchangeName, routingKey);
        }
        // prefetchSize=0表示：消息大小是否限制（听说rabbitMQ没实现这个）
        // prefetchCount=1表示：broker一次性推送给消费端消息个数不大于1
        // global=true/false：表示限制是在channel上还是在consumer上（听说rabbitMQ没实现这个）
        channel.basicQos(0, 1, true);
        //开始监听消息
        Consumer consumer = new DefaultConsumer(channel) {
            @SneakyThrows
            @Override
            public void handleDelivery(String consumerTag,
                                       Envelope envelope,
                                       AMQP.BasicProperties properties,
                                       byte[] body) {
                String message = new String(body, StandardCharsets.UTF_8);
                Thread.sleep(3000);
                log.info("topicConsumer-->routingKey={}-->message={}", envelope.getRoutingKey(), message);
                //设置为true则会确认收到消息，并在队列中删除该消息
                channel.basicAck(envelope.getDeliveryTag(), true);
            }
        };
        while (true) {
            channel.basicConsume(queueName, false, consumer);
        }
    }
}
