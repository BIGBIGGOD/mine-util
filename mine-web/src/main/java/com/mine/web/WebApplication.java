package com.mine.web;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ImportResource;

import com.mine.web.config.SpringCtxManager;

/**
 * @author jqd
 * @Description
 * @version V1.0
 * @Date 2019年10月18日 13:24:47
 * @Copyright Copyright © 2019 深圳花儿绽放网络科技股份有限公司. All rights reserved.
 */

/**
 * 手动扫描注解,当被扫描路径与启动类在同一路径及以下时不用手动添加扫描
 */
@ComponentScan(basePackages = {"com.mine.domain","com.mine.web"})
/**
 * 手动添加mapper扫描，当被扫描路径与启动类在同一路径及以下时不用手动添加扫描
 */
@MapperScan(basePackages = {"com.mine.domain.mapper", "com.mine.security.mapper"})
/**
 * 手动扫描过滤器注解
 */
@ServletComponentScan(basePackages = "com.mine.web.filter")
/**
 * 手动添加项目的xml配置文件
 */
@ImportResource(value = {"classpath:test.xml"})
@SpringBootApplication
public class WebApplication extends SpringBootServletInitializer {
    public static void main(String[] args) {
        SpringApplication.run(WebApplication.class, args);
//        FilterRegistrationBean xxxx = SpringCtxManager.getBean("testFilterRegistration", FilterRegistrationBean.class);
        System.out.println("启动完成");
    }

    /**
     * 重写configure方法作用：不使用spring boot自带的tomcat启动
     */
    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        // 注意这里的参数要指向原先用main方法执行的Application启动类
        return builder.sources(WebApplication.class);
    }
}