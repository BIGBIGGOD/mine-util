package com.mine.domain.service.impl;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mine.domain.mapper.UserDoExtendMapper;
import com.mine.domain.mapper.UserDoMapper;
import com.mine.domain.model.UserDo;
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

    @Autowired
    private UserDoMapper userDoMapper;
    private UserDoExtendMapper userDoExtendMapper;

    @Override
    public void test1() {
        UserDo userDo = new UserDo();
        userDo.setName("快快快看");
        userDoMapper.insert(userDo);

        userDoExtendMapper.addUser(UUID.randomUUID().toString().replace("-", "").substring(6));

        System.out.println("你好啊，service！！");
    }
}
