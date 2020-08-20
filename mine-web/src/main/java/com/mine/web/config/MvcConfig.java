package com.mine.web.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.servlet.config.annotation.*;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

/**
 * @author admin
 * @Description: spring与spring Mvc整合类
 * Created by jiangqd on 2019/1/12.
 */
//@Configuration
//@EnableWebMvc
//@ComponentScan(basePackages = {"com.mine"})
public class MvcConfig implements WebMvcConfigurer {

    /**
     * 注册一个默认的Handler，处理静态文件
     * @param configurer
     */
    @Override
    public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
        configurer.enable();
    }

    /**
     * 配置WEB-INF下静态资源访问
     * @param registry
     */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/resource/**")
            .addResourceLocations("/WEB-INF")
            .setCachePeriod(31556926);
    }

    /**
     * 配置视图解析器
     */
    @Override
    public void configureViewResolvers(ViewResolverRegistry registry) {
        registry.jsp("/WEB-INF", ".jsp");
        registry.enableContentNegotiation(new MappingJackson2JsonView());
    }

    @Override
    public void configureContentNegotiation(ContentNegotiationConfigurer configurer) {
        configurer.favorPathExtension(true)
            .ignoreAcceptHeader(true)
            .parameterName("mediaType")
            .ignoreUnknownPathExtensions(true)
            .defaultContentType(MediaType.TEXT_MARKDOWN)
            .mediaType("html", MediaType.TEXT_HTML)
            .mediaType("json", MediaType.APPLICATION_JSON);
    }

}
