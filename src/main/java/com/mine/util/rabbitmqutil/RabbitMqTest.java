package com.mine.util.rabbitmqutil;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.TimeoutException;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import com.mine.common.BaseJunit4Test;
import com.mine.util.rabbitmqutil.consumer.RabbitConsumer;
import com.mine.util.rabbitmqutil.producer.RabbitProducer;
import com.rabbitmq.client.*;
import lombok.extern.slf4j.Slf4j;

/**
 * @author jiangqingdong
 * @version v1.0
 * @Description rabbitmq测试类
 * @DATE 2020/2/20 14:39
 * @Copyright Copyright © 2019 深圳花儿绽放网络科技股份有限公司. All rights reserved.
 */
@Slf4j
public class RabbitMqTest extends BaseJunit4Test {

    @Autowired
    @Qualifier("mqConnectionFactory")
    private ConnectionFactory connectionFactory;
    @Autowired
    private RabbitConsumer rabbitConsumer;
    @Autowired
    private RabbitProducer rabbitProducer;

    @Test
    public void testGetBean() throws IOException, TimeoutException {
        connectionFactory.newConnection();
        System.out.println("你好啊");
    }

//            AMQP.BasicProperties.Builder propsBuilder = new AMQP.BasicProperties.Builder();
//            propsBuilder.deliveryMode(2);
//            propsBuilder.priority(0);
//            propsBuilder.contentType("text/plain");
//            channel.basicPublish(exchangeName, routingKey, propsBuilder.build(), msg);

    @Test
    public void testProducer() throws IOException, TimeoutException {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setUsername("root");
        factory.setPassword("root");
        factory.setHost("106.52.169.187");
        factory.setPort(5672);
        //connection是socket连接的抽象，为我们处理了通信协议版本协商以及认证
        try (Connection conn = factory.newConnection();
             //创建了一个通道（channel），大部分的API操作均在这里完成
             Channel channel = conn.createChannel()) {

            String exchangeName = "test-exchange";
            channel.exchangeDeclare(exchangeName, "direct", true);

            //定义路由，也就是定义队列名称
            String routingKey = "test_queue";

            //定义发送消息体,消息的内容是一个字节数组，所以你可以随意编码（encode）
            byte[] msg = "hello world".getBytes();

            //直接使用rabbitmq中默认的转化器
            log.info("发送消息");
            channel.basicPublish("", routingKey, null, msg);
        }
    }

    @Test
    public void testConsumer() throws IOException, TimeoutException {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setUsername("root");
        factory.setPassword("root");
        factory.setHost("106.52.169.187");
        try (Connection conn = factory.newConnection();
             Channel channel = conn.createChannel()) {
            String exchangeName = "test-exchange";
            channel.exchangeDeclare(exchangeName, "direct", true);

            //声明要消费的队列,方法属性注释在生产者中有笔记
            String queueName = "test_queue";
            channel.queueDeclare(queueName, false, false, false, null);
            String routingKey = "test_queue";
            channel.queueBind(queueName, exchangeName, routingKey);

            while (true) {
                channel.basicConsume(queueName, false, "", new DefaultConsumer(channel) {
                    @Override
                    public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                        String routingKey = envelope.getRoutingKey();
                        String contentType = properties.getContentType();
                        String bodyStr = new String(body, StandardCharsets.UTF_8);
                        log.info("接收消息");
                        System.out.println("routingKey: " + routingKey + ", contentType: " + contentType + ", body: " + bodyStr);
                        long deliveryTag = envelope.getDeliveryTag();
                        //非自动确认删除模式下需要调用basicAck方法进行消息确认
                        channel.basicAck(deliveryTag, false);
                    }
                });
            }
        }
    }

    @Test
    public void test1() throws IOException, TimeoutException {
        rabbitConsumer.topicConsumer(connectionFactory.newConnection().createChannel(), "test1");
    }

    @Test
    public void test2() throws IOException, TimeoutException {
        rabbitConsumer.topicRetryConsumer(connectionFactory.newConnection().createChannel(), "qqq", "xxx");
    }

    @Test
    public void test3() throws IOException, TimeoutException {
        rabbitProducer.topicProducer(connectionFactory.newConnection().createChannel(), "xxx", "hello" + Math.random());
    }
}
