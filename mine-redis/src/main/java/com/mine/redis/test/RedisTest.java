package com.mine.redis.test;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.mine.redis.common.BaseRedisTest;
import com.mine.redis.util.RedisTemplateUtils;
import lombok.extern.slf4j.Slf4j;

/**
 * redis测试
 *
 * @author jiangqd
 * @date 2019/1/12
 */
@Slf4j
public class RedisTest extends BaseRedisTest {

    @Autowired
    private RedisTemplateUtils redisTemplateUtils;

    @Test
    public void test() {
        redisTemplateUtils.set("aaaa", "bbbb");
        String str = redisTemplateUtils.get("aaaa");
        System.out.println(str);
    }

}
