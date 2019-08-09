package com.mine.filter;

/**
 * Created by jiangqingdong on 2017/1/16 16:30.
 */

import java.io.IOException;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@WebFilter(filterName = "myFilter", urlPatterns = {"/test/*"})
public class MyFilter implements Filter {

//    private PlatformService platformService = SpringContextManager.getBean(PlatformService.class);

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

        log.info("进入过滤器：MyFilter");

        // 转化对应的request和response
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse rep = (HttpServletResponse) response;

        // 取得根目录所对应的绝对路径:
        String currentURL = req.getRequestURI();

        // 获取token
        String token = req.getParameter("token");
        log.info("token={}", token);

        if (StringUtils.isBlank(token)) {
            log.error("请求参数token={}不能为空", token);
            rep.sendRedirect("/index.jsp");
            return;
        }
        log.info("通过myFilter过滤器");
        chain.doFilter(req, rep);
    }

    @Override
    public void destroy() {

    }
}
