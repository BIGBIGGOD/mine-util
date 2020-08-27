package com.mine.web.config;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.mine.web.filter.MyFilter;

/**
 * @author jiangqingdong
 * @version v1.0
 * @Description
 * @DATE 2020/8/27 0027 16:31
 * @Copyright Copyright © 2019 深圳花儿绽放网络科技股份有限公司. All rights reserved.
 */
@Configuration
public class FilterConfig {

    /**
     * 配置过滤器bean
     *
     * @param
     */
    @Bean
    public FilterRegistrationBean<MyFilter> testFilterRegistration() {
        FilterRegistrationBean<MyFilter> registration = new FilterRegistrationBean<MyFilter>();
        registration.setFilter(new MyFilter());
        registration.addUrlPatterns("/test");
        registration.setName("myFilter");
        registration.setOrder(0);
        System.out.println("初始化myFilter");
        return registration;
    }
}
