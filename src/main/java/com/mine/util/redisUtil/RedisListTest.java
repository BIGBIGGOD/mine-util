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
public class RedisListTest extends BaseJunit4Test {

    @Autowired
    private RedisListUtil redisListUtil;

    @Test
    public void test1() {
        redisListUtil.save1("测试1");
    }
    @Test
    public void test2() {
        redisListUtil.save2("[\"测试2\",\"测试3\",\"测试4\"]");
    }
    @Test
    public void test3() {
        String res = redisListUtil.query1(0);
    }
    @Test
    public void test4() {
        String res = redisListUtil.query2();
    }
    @Test
    public void test5() {
        Long res = redisListUtil.query3();
    }


}
