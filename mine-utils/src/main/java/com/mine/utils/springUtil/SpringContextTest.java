package com.mine.utils.springUtil;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.mine.common.entity.User;

/**
 * @author jiangqingdong
 * @version v1.0
 * @Description
 * @DATE 2019/8/2 10:39
 * @Copyright Copyright © 2019 深圳花儿绽放网络科技股份有限公司. All rights reserved.
 */
public class SpringContextTest {

    public static void main(String[] args) {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("spring-test.xml");
        User user1 = (User) context.getBean("user1");
        User user2 = (User) context.getBean("user2");
        User user3 = (User) context.getBean("user3");
        User user4 = (User) context.getBean("user4");
        UserBeanFactory beanFactory = (UserBeanFactory) context.getBean("userBeanFactory");
        User user5 = (User)beanFactory.getBean("xx");
        System.out.println("");
    }
}
