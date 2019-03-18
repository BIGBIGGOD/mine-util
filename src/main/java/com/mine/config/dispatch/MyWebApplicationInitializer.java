package com.mine.config.dispatch;

import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;

/**
 * @author gaoxx gaoxx@fxiaoke.com
 * @ClassName: MyWebApplicationInitializer
 * @Description: 初始化web环境
 * @datetime 2019/1/2 13:13
 * @Version 1.0
 */
public class MyWebApplicationInitializer implements WebApplicationInitializer {
    /**
     * web程序入口
     * @param servletContext
     * @throws ServletException
     */
    @Override
    public void onStartup(ServletContext servletContext) {
        AnnotationConfigWebApplicationContext applicationContext = new AnnotationConfigWebApplicationContext();
        applicationContext.register(DispatcherServletInitializer.class);
        applicationContext.refresh();

        DispatcherServlet dispatcherServlet = new DispatcherServlet(applicationContext);
        ServletRegistration.Dynamic registration = servletContext.addServlet("app", dispatcherServlet);
        registration.addMapping("/");
        registration.setLoadOnStartup(0);
    }
}
