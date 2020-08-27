//package com.mine.redis.test;
//
//import org.junit.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//
//import com.mine.redis.common.BaseRedisTest;
//import lombok.extern.slf4j.Slf4j;
//
///**
// * redis测试
// *
// * @author jiangqd
// * @date 2019/1/12
// */
//@Slf4j
//public class RedisStringTest extends BaseRedisTest {
//
//    @Autowired
//    private RedisStringUtil redisStringUtil;
//
//    @Test
//    public void test1() {
//        redisStringUtil.save1("测试1");
//    }
//
//    @Test
//    public void test2() {
//        boolean res = redisStringUtil.save2("测试1");
//    }
//
//    @Test
//    public void test3() {
//        redisStringUtil.save3("[\"测试2\",\"测试3\"]");
//    }
//
//    @Test
//    public void test4() {
//        redisStringUtil.query1("[\"测试2\",\"测试3\"]");
//    }
//
//    @Test
//    public void test5() {
//        redisStringUtil.query2("[\"测试2\",\"测试3\"]");
//    }
//
//    @Test
//    public void test6() {
//        redisStringUtil.save1("测试1");
//        String str = redisStringUtil.query3("测试1");
//        System.out.println(str);
//    }
//}
