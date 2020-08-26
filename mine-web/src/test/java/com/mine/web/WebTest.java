package com.mine.web;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.mine.redis.redisUtil.RedisListUtil;
import lombok.extern.slf4j.Slf4j;

/**
 * @author jiangqingdong
 * @version v1.0
 * @Description
 * @DATE 2020/8/26 0026 10:41
 * @Copyright Copyright © 2019 深圳花儿绽放网络科技股份有限公司. All rights reserved.
 */
@Slf4j
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:applicationContext.xml"})
public class WebTest {

    @Test
    public void test() {
        System.out.println("你好啊");
    }

    @Autowired
    private RedisListUtil redisListUtil;
    @Test
    public void test1() {
        redisListUtil.save1("测试2222");
    }
}
