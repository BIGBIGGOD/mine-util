package com.mine.domain.service.impl;

import org.springframework.stereotype.Service;

import com.mine.domain.service.TestService;


/**
 * @author jiangqingdong
 * @version v1.0
 * @Description
 * @DATE 2019/10/31 14:16
 * @Copyright Copyright © 2019 深圳花儿绽放网络科技股份有限公司. All rights reserved.
 */
@Service
public class TestServiceImpl implements TestService {
    @Override
    public void test1() {
//        int a = 1/0;
        System.out.println("你好啊，service！！");
    }
}
