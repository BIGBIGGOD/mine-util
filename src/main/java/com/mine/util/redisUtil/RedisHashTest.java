package com.mine.util.redisUtil;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.mine.common.BaseJunit4Test;
import lombok.extern.slf4j.Slf4j;

/**
 * redis测试
 *
 * @author jiangqd
 * @date 2019/1/12
 */
@Slf4j
public class RedisHashTest extends BaseJunit4Test {

    @Autowired
    private RedisHashUtil redisHashUtil;

    @Test
    public void test1() {
        redisHashUtil.save1();
    }

    @Test
    public void test2() {
        redisHashUtil.query1();
    }

    @Test
    public void test3() {
        redisHashUtil.update1(999);
    }

    @Test
    public void test4() {
        Long res = redisHashUtil.query2();
    }

}
