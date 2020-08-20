package com.mine.web.intercept;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.ui.ModelMap;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import lombok.extern.slf4j.Slf4j;

/**
 * @author jiangqingdong
 * @version v1.0
 * @Description 拦截器设置, spring-intercept.xml有相应拦截路劲配置
 * 1.过滤器是依赖于Servlet容器,基于回调函数,Intercepto依赖与框架,基于反射机制
 * 2.过滤器的过滤范围更大,还可以过滤一些静态资源,拦截器只拦截请求
 * @DATE 2019/8/9 11:51
 */
@Slf4j
public class MyIntercept implements HandlerInterceptor {

    /**
     * 请求之前执行，可以进行一些参数校验、乱码处理
     *
     * @param request
     * @param response
     * @param handler
     * @return 返回Boolean类型表示是否通过拦截
     * @throws Exception
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        log.info("进入preHandle");

        String token = request.getParameter("token");
        log.info("token={}", token);
        if (StringUtils.isBlank(token)) {
            return true;
        }
        return true;

    }

    /**
     * 该方法在action执行后，生成视图前执行,在这里可以修改视图数据
     *
     * @param request
     * @param response
     * @param handler
     * @param modelAndView
     * @throws Exception
     */
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

        log.info("进入postHandle");
        ModelMap model = new ModelMap();
        model.put("测试", "ceshi");
        if (modelAndView == null) {
            modelAndView = new ModelAndView();
        }
        modelAndView.addAllObjects(model);

    }

    /**
     * 最后执行，通常用于结束关闭资源、处理异常
     *
     * @param request
     * @param response
     * @param handler
     * @param ex
     * @throws Exception
     */
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

        log.info("进入afterCompletion");

    }
}
