//package com.mine.rabbitmq.runner;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.CommandLineRunner;
//import org.springframework.stereotype.Component;
//
//import com.huaer.common.constant.TopicTitleConstant;
//import com.huaer.distribution.config.ActiveMqConfig;
//import com.huaer.distribution.listener.SettleListener;
//import com.huaer.entity.TopicEnum;
//import com.huaer.service.ConsumerService;
//import lombok.extern.slf4j.Slf4j;
//
///**
// * @author jiangqingdong
// * @version v1.0
// * @Description spring boot中使用CommandLineRunner进行项目启动后开始运行，用于队列消息监听
// * @DATE 2020/6/4 0004 21:13
// * @Copyright Copyright © 2019 深圳花儿绽放网络科技股份有限公司. All rights reserved.
// */
//@Component
//@Slf4j
//public class SpringBootStyletRunner implements CommandLineRunner {
//
//    @Autowired
//    private ConsumerService consumerService;
//
//    @Autowired
//    private ActiveMqConfig activeMqConfig;
//
//    @Autowired
//    private SettleListener settleListener;
//
//    @Override
//    public void run(String... args) {
//            consumerService.subMessage(TopicTitleConstant.OUTPUT_TITLE,
//                    settleListener,
//                    TopicEnum.TOPIC_TYPE.getCode(),
//                    activeMqConfig.getActivemqClientId());
//            log.info("监听器启动完成");
//    }
//
//}
