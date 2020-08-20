//package com.mine.util.rabbitmqutil.listener;
//
//import javax.jms.JMSException;
//import javax.jms.Message;
//import javax.jms.MessageListener;
//import javax.jms.TextMessage;
//
//import org.springframework.stereotype.Component;
//
//import com.huaer.distribution.domain.util.SettleThread;
//import lombok.extern.slf4j.Slf4j;
//
///**
// * @author jiangqingdong
// * @version v1.0
// * @Description 监听消息内容解析
// * @DATE 2020/6/4 0004 21:13
// * @Copyright Copyright © 2019 深圳花儿绽放网络科技股份有限公司. All rights reserved.
// */
//@Slf4j
//@Component
//public class SpringBootStyletListener implements MessageListener {
//
//    @Override
//    public void onMessage(Message message) {
//    	try {
//    		if(message instanceof TextMessage) {
//    			long start = System.currentTimeMillis();
//    			String json = ((TextMessage) message).getText();
//    			log.info("接收到消息内容 json={}", json);
//
//    			SettleThread settleThread = new SettleThread(message);
//    			settleThread.run();
//
//    			log.info("消息处理完毕:耗时:{},详情为:{}",System.currentTimeMillis() - start,json);
//    		}
//		} catch (JMSException e) {
//			log.error("消息处理失败",e);
//		}
////        ThreadPoolUtil.THREAD_POOL_EXECUTOR.execute(new SettleThread(message));
//    }
//
//}
