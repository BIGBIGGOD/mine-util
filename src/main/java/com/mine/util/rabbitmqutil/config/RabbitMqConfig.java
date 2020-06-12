package com.mine.util.rabbitmqutil.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import com.rabbitmq.client.ConnectionFactory;

/**
 * @author jiangqingdong
 * @version v1.0
 * @Description mqbean管理
 * @DATE 2020/6/5 0005 15:55
 * @Copyright Copyright © 2019 深圳花儿绽放网络科技股份有限公司. All rights reserved.
 */
@Component
public class RabbitMqConfig {

    @Autowired
    private RabbitMqParams rabbitMqParams;

    @Bean
    public ConnectionFactory mqConnectionFactory() {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setUsername(rabbitMqParams.getUsername());
        factory.setPassword(rabbitMqParams.getUsername());
        factory.setHost(rabbitMqParams.getHost());
//        factory.setPort(rabbitMqParams.getPort());
        return factory;
    }

}
