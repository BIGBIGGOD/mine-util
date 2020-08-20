package com.mine.rabbitmq.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import lombok.Data;

/**
 * @author jiangqingdong
 * @version v1.0
 * @Description mq配置参数
 * @DATE 2020/5/29 0029 11:37
 * @Copyright Copyright © 2019 深圳花儿绽放网络科技股份有限公司. All rights reserved.
 */
@Component
@Data
public class RabbitMqParams {

    @Value("${rabbit.mq.username}")
    private String username;
    @Value("${rabbit.mq.password}")
    private String password;
    @Value("${rabbit.mq.host}")
    private String host;
    @Value("${rabbit.mq.port}")
    private int port;

}
