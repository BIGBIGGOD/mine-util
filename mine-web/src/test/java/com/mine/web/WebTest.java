package com.mine.web;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import lombok.extern.slf4j.Slf4j;

/**
 * @author jiangqingdong
 * @version v1.0
 * @Description
 * @DATE 2020/8/26 0026 10:41
 * @Copyright Copyright © 2019 深圳花儿绽放网络科技股份有限公司. All rights reserved.
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = {WebApplication.class})
@Slf4j
public class WebTest {

    @Test
    public void test() {
        System.out.println("你好啊");
    }
}
