package com.mine.web.filter;

import java.io.IOException;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.core.annotation.Order;

import lombok.extern.slf4j.Slf4j;

/**
 * @author jiangqingdong
 * @version v1.0
 * @Description 跨域拦截器
 * @DATE 2019/11/21 17:01
 * @Copyright Copyright © 2019 深圳花儿绽放网络科技股份有限公司. All rights reserved.
 */
@Order(0)
@Slf4j
@WebFilter(filterName = "corsFilter", urlPatterns = "/*")
public class CorsFilter implements Filter {

    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {
        log.info("进入cors过滤器");
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) res;
        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, DELETE, PUT, GET");
        response.setHeader("Access-Control-Max-Age", "3600");
        response.setHeader("Access-Control-Allow-Headers", "x-requested-with");
        chain.doFilter(req, res);
    }

    @Override
    public void init(FilterConfig filterConfig) {
    }

    @Override
    public void destroy() {
    }
}
