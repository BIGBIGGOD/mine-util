package com.mine.filter;

import java.io.IOException;

import javax.servlet.*;

/**
 * @author jiangqingdong
 * @Description 示例SpringBoot中怎么使用filter
 * 定义一个实现了Filter的过滤器，不用添加任何的注解
 * 在一个专门的配置类中注册该过滤器，并配置其过滤路径，示例如本类中最下方注释代码所示
 * 如果在SpringBoot中使用WebFilter的方式创建过滤器，则需要在SpringBoot的启动类上添加注解：@ServletComponentScan(basePackages = "com.huaer.distribution.web.filter")
 * @DATE 2020/3/31 19:56
 */
public class SpringBootFiler implements Filter {


    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

    }

    @Override
    public void destroy() {

    }
}

//@Configuration
//public class SpringBootFilerConfig {
//    @Bean
//    public Filter springBootFiler() {
//        return new SpringBootFiler();
//    }
//
//    @Bean
//    public FilterRegistrationBean<DelegatingFilterProxy> springBootFilerRegistration() {
//        FilterRegistrationBean<DelegatingFilterProxy> registration = new FilterRegistrationBean<>();
//        registration.setFilter(new DelegatingFilterProxy("springBootFiler"));
//        registration.addUrlPatterns("/*");
//        registration.setName("springBootFiler");
//        registration.setOrder(Ordered.LOWEST_PRECEDENCE);
//        return registration;
//    }
//
//    @Bean
//    public Filter distributionCmbMobileUniqueOauthFilter() {
//        return new DistributionCmbMobileUniqueOauthFilter();
//    }
//
//    @Bean
//    public FilterRegistrationBean<DelegatingFilterProxy> distributionCmbMobileUniqueOauthFilterRegistration() {
//        FilterRegistrationBean<DelegatingFilterProxy> registration = new FilterRegistrationBean<>();
//        registration.setFilter(new DelegatingFilterProxy("distributionCmbMobileUniqueOauthFilter"));
//        registration.addUrlPatterns("/mobile/*", "/user/*");
//        registration.setName("distributionCmbMobileUniqueOauthFilter");
//        registration.setOrder(1);
//        return registration;
//    }
//}
