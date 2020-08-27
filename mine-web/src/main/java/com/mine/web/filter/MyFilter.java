package com.mine.web.filter;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;

import com.google.common.collect.ImmutableSet;
import lombok.extern.slf4j.Slf4j;

/**
 * @author jiangqingdong
 * @Description 示例SpringBoot中怎么使用filter
 * 定义一个实现了Filter的过滤器，不用添加任何的注解
 * 在一个专门的配置类中注册该过滤器，并配置其过滤路径，示例如本类中最下方注释代码所示
 * 如果在SpringBoot中使用WebFilter的方式创建过滤器，则需要在SpringBoot的启动类上添加注解：@ServletComponentScan(basePackages = "com.huaer.distribution.web.filter")
 * @DATE 2020/3/31 19:56
 */
@Slf4j
public class MyFilter implements Filter {

    /**
     * 定义不需要拦截的路径
     */
    private static ImmutableSet<String> urlSet = ImmutableSet.of("/test/test1");

    @Override
    public void init(FilterConfig filterConfig) {

    }

    //定义需要加载的bean
    /*private static PlatformService platformService;

    static{
        //获取当前web上下文
        WebApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        //从上下文中获取bean
        platformService = ctx.getBean(PlatformService.class);
    }*/

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) {

        log.info("进入过滤器：myFilter");

        // 转化对应的request和response
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse rep = (HttpServletResponse) response;

        try {
            // 取得根目录所对应的绝对路径:
            String currentURL = req.getRequestURI();
            log.info("currentURL={}", currentURL);
            if (urlSet.contains(currentURL)) {
                log.info("不拦截url={}", currentURL);
                chain.doFilter(req, rep);
            }


            // 获取token
            String token = req.getParameter("token");
            log.info("token={}", token);

            if (StringUtils.isBlank(token)) {
                log.error("请求参数token={}不能为空", token);
//            rep.sendRedirect("/index.jsp");
                chain.doFilter(req, rep);
                return;
            }
            log.info("通过myFilter过滤器");
            chain.doFilter(req, rep);
        } catch (Exception e) {
            log.error("myFilter过滤器报错,e", e);
        }

    }

    @Override
    public void destroy() {

    }
}
