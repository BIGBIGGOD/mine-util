package com.mine.util.rabbitmqutil.producer;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import com.google.common.collect.Maps;
import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.BuiltinExchangeType;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.MessageProperties;
import lombok.extern.slf4j.Slf4j;

/**
 * @author jiangqingdong
 * @version v1.0
 * @Description
 * @DATE 2020/6/4 0004 21:12
 * @Copyright Copyright © 2019 深圳花儿绽放网络科技股份有限公司. All rights reserved.
 */
@Slf4j
@Component
public class RabbitProducer {

    /**
     * 默认路由器
     * @param channel
     * @param message
     * @throws IOException
     */
    public void generalProducer(Channel channel, String message) throws IOException {

        //定义队列名称
        String queueName = "general_queue_name";
        //该队列是否持久化（即是否保存到磁盘中）
        boolean durable = false;
        //该队列是否为该通道独占的，即其他通道是否可以消费该队列
        boolean exclusive = false;
        //该队列不再使用的时候，是否让RabbitMQ服务器自动删除掉
        boolean autoDelete = false;
        //其他参数
        Map<String, Object> argument = Maps.newHashMap();
        //声明队列，生产者和消费者最好都声明，否则可能会因两端启动顺序问题导致消息丢失，其中参数如上所示
        channel.queueDeclare(queueName, durable, exclusive, autoDelete, argument);

        //路由器的名字，即将消息发到哪个路由器，当为空字符串时表示直接使用rabbitmq中默认的路由器
        String exchange = "";
        //路由键，即发布消息时，该消息的路由键是什么，因为使用的是默认的路由器所以该属性对应是上面声明队列时的队列名，实际上路由键不等于队列名，路由器可以绑定多个路由键，路由键又可以绑定多个队列
        String routingKey = queueName;
        //指定消息的基本属性,注意类型是com.rabbitmq.client.AMQP.BasicProperties
        AMQP.BasicProperties arguments = null;
        //消息体，也就是消息的内容，是字节数组
        byte[] body = message.getBytes(StandardCharsets.UTF_8);

        //发布消息：只用在生产者
        channel.basicPublish(exchange, routingKey, arguments, body);
        log.info("generalProducer-->message={}", message);
    }

    /**
     * fanout类型路由器
     * @param channel
     * @param message
     * @throws IOException
     */
    public void fanoutProducer(Channel channel, String message) throws IOException {
        //路由器的名字
        String exchangeName = "fanout_exchange_name";
        //路由器的类型：topic、direct、fanout、header
        //fanout：会忽视绑定键，每个消费者都可以接受到所有的消息（前提是每个消费者都要有各自单独的队列，而不是共有同一队列）。
        //direct：只有绑定键和路由键完全匹配时，才可以接受到消息。
        //topic：可以设置多个关键词作为路由键，在绑定键中可以使用*和#来匹配
        //headers：（可以忽视它的存在）
        BuiltinExchangeType type = BuiltinExchangeType.FANOUT;
        //是否持久化该路由器
        boolean durable = true;
        //是否自动删除该路由器
        boolean autoDelete = false;
        //是否是内部使用的，true的话客户端不能使用该路由器
        boolean internal = false;
        //其他参数
        Map<String, Object> props = null;
        //声明路由以及路由的类型,这里声明的是fanout类型，会将消息广播到该路由器绑定的所有队列中，对应在生产者发布消息的时候不需要指定任何队列名，也就是路由key直接给空字符串即可
        channel.exchangeDeclare(exchangeName, type);


        //路由键，即发布消息时，该消息的路由键是什么
        String routingKey = "";
        //指定消息的基本属性,注意类型是com.rabbitmq.client.AMQP.BasicProperties
        AMQP.BasicProperties arguments = null;
        //消息体，也就是消息的内容，是字节数组
        byte[] body = message.getBytes(StandardCharsets.UTF_8);

        //发布消息：只用在生产者
        channel.basicPublish(exchangeName, routingKey, arguments, body);
        log.info("fanoutProducer-->message={}", message);
    }

    /**
     * direct类型路由器
     * @param channel
     * @param message
     * @throws IOException
     */
    public void directProducer(Channel channel, String message) throws IOException {

        //路由器的名字
        String exchangeName = "direct_exchange_name";

        //声明DIRECT类型的路由器,该类型路由器只会将消息放到指定路由key的队列中
        channel.exchangeDeclare(exchangeName, BuiltinExchangeType.DIRECT);

        //路由键
        String routingKey = "directRoutingKey";

        //指定消息的基本属性,注意类型是com.rabbitmq.client.AMQP.BasicProperties
        AMQP.BasicProperties arguments = null;

        //消息体，也就是消息的内容，是字节数组
        byte[] body = message.getBytes(StandardCharsets.UTF_8);

        //发布消息
        channel.basicPublish(exchangeName, routingKey, arguments, body);
        log.info("directProducer-->message={}", message);
    }

    /**
     * topic类型路由器
     * @param channel
     * @param routingKey
     * @param message
     * @throws IOException
     */
    public void topicProducer(Channel channel, String routingKey, String message) throws IOException {

        //路由器的名字
//        String exchangeName = "topic_exchange_name" + "@retry";
        String exchangeName = "topic_exchange_name";

        //声明topic类型的路由器,该类型路由器只会将消息放到指定路由key的队列中
        channel.exchangeDeclare(exchangeName, BuiltinExchangeType.TOPIC, false);

        //topic类型的路由器路由键设置与其它路由器不一致的地方在于路由键可以模糊匹配,其它基本一样
        //该类型路由器的路由键必须是一些单词的集合，中间用点号.分割。这些单词可以是任意的，但通常会体现出消息的特征，如：kern.critical,#,kern.*,*.critical
        //其中匹配规则是#代表所有，*只代表一个单词且不能为空，整个路由键长度不能超过255
        if (StringUtils.isBlank(routingKey)) {
            routingKey = "product.info";
        }

        //指定消息的基本属性,注意类型是com.rabbitmq.client.AMQP.BasicProperties
        AMQP.BasicProperties arguments = MessageProperties.PERSISTENT_BASIC;

        //消息体，也就是消息的内容，是字节数组
        byte[] body = message.getBytes(StandardCharsets.UTF_8);

        //发布消息
        channel.basicPublish(exchangeName, routingKey, arguments, body);
        log.info("topicProducer-->message={}", message);
    }

    /**
     * topic类型路由器,多种参数配置
     * @param channel
     * @param routingKey
     * @param message
     * @throws IOException
     */
    public void topicParamsProducer(Channel channel, String routingKey, String message) throws IOException, InterruptedException {

        //设置confirm确认rabbitmq收到发送的消息
        channel.confirmSelect();
        //路由器的名字
        String exchangeName = "params_exchange_name";

        //声明topic类型的路由器,该类型路由器只会将消息放到指定路由key的队列中
        channel.exchangeDeclare(exchangeName, BuiltinExchangeType.TOPIC, false);

        //topic类型的路由器路由键设置与其它路由器不一致的地方在于路由键可以模糊匹配,其它基本一样
        //该类型路由器的路由键必须是一些单词的集合，中间用点号.分割。这些单词可以是任意的，但通常会体现出消息的特征，如：kern.critical,#,kern.*,*.critical
        //其中匹配规则是#代表所有，*只代表一个单词且不能为空，整个路由键长度不能超过255
        if (StringUtils.isBlank(routingKey)) {
            routingKey = "product.info";
        }

        //指定消息的基本属性,注意类型是com.rabbitmq.client.AMQP.BasicProperties
        AMQP.BasicProperties arguments = MessageProperties.PERSISTENT_BASIC;

        //消息体，也就是消息的内容，是字节数组
        byte[] body = message.getBytes(StandardCharsets.UTF_8);

        //发布消息
        channel.basicPublish(exchangeName, routingKey, arguments, body);

        // 查询rabbitmq是否接收到消息，回调返回true或者false，否则重发该消息
        if (channel.waitForConfirms()) {
            log.info("发送成功");
        }else {
            log.error("发送失败");
        }
        log.info("topicParamsProducer-->message={}", message);
    }

    /**
     * 死信队列以及延伸到延时队列的模拟实现，消费者端最后监听死信队列而不是普通队列
     * @param channel channel
     * @throws IOException 异常
     */
    public void deadConsumer(Channel channel) throws IOException {

        // 声明一个接收被删除的消息的交换机和队列 ，即声明死信队列相关信息
        String exchangeDeadName = "exchange.dead";
        String queueDeadName = "queue_dead";
        channel.exchangeDeclare(exchangeDeadName, BuiltinExchangeType.DIRECT);
        channel.queueDeclare(queueDeadName, false, false, false, null);
        channel.queueBind(queueDeadName, exchangeDeadName, "routingkey.dead");

        //声明一个普通交换器以及队列
        String exchangeName = "exchange.fanout";
        String queueName = "queue_name";
        channel.exchangeDeclare(exchangeName, BuiltinExchangeType.FANOUT);
        //设置普通队列的参数，其中过期时间以及最大空间设置用于判断消息是否转发到死信队列，以及声明设置对应转发到死信队列的交换器名以及路由键
        Map<String, Object> arguments = new HashMap<>(7);
        // 统一设置队列中的所有消息的过期时间
        arguments.put("x-message-ttl", 30000);
        // 设置超过多少毫秒没有消费者来访问队列，就删除队列的时间
        arguments.put("x-expires", 20000);
        // 设置队列的最新的N条消息，如果超过N条，前面的消息将从队列中移除掉
        arguments.put("x-max-length", 4);
        // 设置队列的内容的最大空间，超过该阈值就删除之前的消息
        arguments.put("x-max-length-bytes", 1024);
        // 将删除的消息推送到指定的交换机，一般x-dead-letter-exchange和x-dead-letter-routing-key需要同时设置
        arguments.put("x-dead-letter-exchange", "exchange.dead");
        // 将删除的消息推送到指定的交换机对应的路由键
        arguments.put("x-dead-letter-routing-key", "routingkey.dead");
        // 设置消息的优先级，优先级大的优先被消费
        arguments.put("x-max-priority", 10);
        channel.queueDeclare(queueName, false, false, false, arguments);
        channel.queueBind(queueName, exchangeName, "");
        String message = "Hello RabbitMQ: ";

        for (int i = 1; i <= 5; i++) {
            // expiration: 设置单条消息的过期时间
            AMQP.BasicProperties.Builder properties = new AMQP.BasicProperties().builder()
                    .priority(i).expiration(i * 1000 + "");
            channel.basicPublish(exchangeName, "", properties.build(), (message + i).getBytes(StandardCharsets.UTF_8));
        }
    }
}
