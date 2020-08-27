package com.mine.web.filter;

/**
 * @author jiangqingdong
 * @version v1.0
 * @Description 过滤器设置
 * @DATE 2019/8/9 11:51
 */

import java.io.IOException;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;

import com.google.common.collect.ImmutableSet;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@WebFilter(filterName = "testFilter", urlPatterns = {"/test/*"})
public class TestFilter implements Filter {

    /**
     * 定义不需要拦截的路径
     */
    private static ImmutableSet<String> urlSet = ImmutableSet.of("/test");

    @Override
    public void init(FilterConfig filterConfig) {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

        log.info("进入过滤器：testFilter");

        // 转化对应的request和response
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse rep = (HttpServletResponse) response;

        // 取得根目录所对应的绝对路径:
        String currentURL = req.getRequestURI();
        log.info("currentURL={}", currentURL);

        // 获取token
        String token = req.getParameter("token");
        log.info("token={}", token);

        if (StringUtils.isBlank(token)) {
            log.error("请求参数token={}不能为空", token);
//            rep.sendRedirect("/index.jsp");
            chain.doFilter(req, rep);
            return;
        }
        log.info("通过testFilter过滤器");
        chain.doFilter(req, rep);
    }

    @Override
    public void destroy() {

    }
}
