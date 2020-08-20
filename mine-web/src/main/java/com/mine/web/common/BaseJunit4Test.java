package com.mine.web.common;

import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import lombok.extern.slf4j.Slf4j;

/**
 * @Description 测试基类
 * Created by jiangqd on 2019/1/12.
 */
@Slf4j
@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration(classes = AppConfig.class)
@ContextConfiguration(locations = {"classpath:applicationContext.xml"})
public class BaseJunit4Test {

}
