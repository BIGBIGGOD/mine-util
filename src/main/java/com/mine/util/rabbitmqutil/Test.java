package com.mine.util.rabbitmqutil;

import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

/**
 * @author jiangqingdong
 * @version v1.0
 * @Description
 * @DATE 2020/6/8 0008 20:38
 * @Copyright Copyright © 2019 深圳花儿绽放网络科技股份有限公司. All rights reserved.
 */
public class Test {
    public static void main(String[] args) throws Exception {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setUsername("root");
        factory.setPassword("root");
        factory.setHost("106.52.169.187");
//        factory.setPort(4369);
        Connection conn = factory.newConnection();
    }
}
