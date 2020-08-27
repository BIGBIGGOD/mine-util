package com.mine.redis.common;

import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.mine.redis.RedisApplication;
import lombok.extern.slf4j.Slf4j;

/**
 * @author Administrator
 * @Description 测试基类
 * Created by jiangqd on 2019/1/12.
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = {RedisApplication.class})
@Slf4j
public class BaseRedisTest {

}
