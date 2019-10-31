package com.mine.aops;


import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

/**
 * @author jqd
 * @version 1.0
 * @Description: 日志管理
 * @date 2016年7月6日  下午5:36:38
 */
@Aspect
@Component
@Slf4j
public class LoggerAdvice {

    @Before("within(com.mine..*) && @annotation(loggerManage)")
    public void addBeforeLogger(JoinPoint joinPoint, LoggerManage loggerManage) {
        log.info("执行 {{}} 开始", loggerManage.description());
        log.info(joinPoint.getSignature().toString());
        log.info("参数={}", parseParames(joinPoint.getArgs()));
    }

    @AfterReturning("within(com.mine..*) && @annotation(loggerManage)")
    public void addAfterReturningLogger(JoinPoint joinPoint, LoggerManage loggerManage) {
        log.info("执行 {{}} 结束", loggerManage.description());
    }

    @AfterThrowing(pointcut = "within(com.mine..*) && @annotation(loggerManage)", throwing = "ex")
    public void addAfterThrowingLogger(JoinPoint joinPoint, LoggerManage loggerManage, Exception ex) {
        log.error("执行 {{}} 异常", loggerManage.description(), ex);
    }

    private String parseParames(Object[] parames) {
        if (null == parames || parames.length <= 0 || parames.length > 1024) {
            return "";
        }
        return ToStringBuilder.reflectionToString(parames, ToStringStyle.SHORT_PREFIX_STYLE);
//        StringBuffer param = new StringBuffer();
//        for (Object obj : parames) {
//            param.append(ToStringBuilder.reflectionToString(obj, ToStringStyle.SHORT_PREFIX_STYLE)).append("  ");
//        }
//        return param.toString();
    }
}
