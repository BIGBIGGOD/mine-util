package com.mine.mybatis;

import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.mine.mybatis.common.BaseMybatisTest;
import com.mine.mybatis.mapper.UserMapper;
import com.mine.mybatis.model.UserDo;
import lombok.extern.slf4j.Slf4j;

/**
 * @author jiangqingdong
 * @version v1.0
 * @Description
 * @DATE 2020/8/26 0026 10:41
 * @Copyright Copyright © 2019 深圳花儿绽放网络科技股份有限公司. All rights reserved.
 */
@Slf4j
public class MybatisTest extends BaseMybatisTest {

    @Autowired
    private UserMapper userMapper;
//    @Autowired
//    private UserExtendMapper userExtendMapper;

    @Test
    public void testSelect() {
        System.out.println(("----- selectAll method test ------"));
        List<UserDo> userList = userMapper.selectList(null);
        userList.forEach(System.out::println);
    }

//    @Test
//    public void test() {
//        List<String> userList = userExtendMapper.getName();
//        userList.forEach(System.out::println);
//        System.out.println("你好啊");
//    }
}
