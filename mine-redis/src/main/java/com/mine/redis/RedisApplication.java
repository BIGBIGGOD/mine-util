package com.mine.redis;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.context.ContextLoader;
import org.springframework.web.context.WebApplicationContext;

/**
 * @author jqd
 * @version V1.0
 * @Description
 * @Date 2019年10月18日 13:24:47
 * @Copyright Copyright © 2019 深圳花儿绽放网络科技股份有限公司. All rights reserved.
 */
@SpringBootApplication
public class RedisApplication extends SpringBootServletInitializer {
    public static void main(String[] args) {
        SpringApplication.run(RedisApplication.class, args);
        System.out.println("启动完成");
    }

    /**
     * 重写configure方法作用：不使用spring boot自带的tomcat启动
     */
    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        // 注意这里的参数要指向原先用main方法执行的Application启动类
        return builder.sources(RedisApplication.class);
    }
}