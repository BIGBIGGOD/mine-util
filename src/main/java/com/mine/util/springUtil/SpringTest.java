package com.mine.util.springUtil;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.mine.model.User;

/**
 * @author jiangqingdong
 * @version v1.0
 * @Description
 * @DATE 2019/8/2 10:39
 * @Copyright Copyright © 2019 深圳花儿绽放网络科技股份有限公司. All rights reserved.
 */
public class SpringTest {

    public static void main(String[] args) {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("springtest.xml");
        User user = context.getBean(User.class);
        System.out.println(user);
    }
}
