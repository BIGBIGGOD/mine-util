package com.mine.mybatis;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

/**
 * @author jqd
 * @version V1.0
 * @Description
 * @Date 2019年10月18日 13:24:47
 * @Copyright Copyright © 2019 深圳花儿绽放网络科技股份有限公司. All rights reserved.
 */
@MapperScan("com.baomidou.mybatisplus.samples.quickstart.mapper")
@SpringBootApplication
public class MybatisApplication extends SpringBootServletInitializer {
    public static void main(String[] args) {
        SpringApplication.run(MybatisApplication.class, args);
        System.out.println("MybatisApplication启动完成");
    }

    /**
     * 重写configure方法作用：不使用spring boot自带的tomcat启动
     */
    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        // 注意这里的参数要指向原先用main方法执行的Application启动类
        return builder.sources(MybatisApplication.class);
    }
}