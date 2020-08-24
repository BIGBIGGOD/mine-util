package com.mine.web.aops;

import java.lang.annotation.*;

/**
 * @author jqd
 * @version 1.0
 * @Description 日志注解
 * @date 2016年7月7日  上午11:34:57
 */
//注释作用对象
@Target(ElementType.METHOD)
//在运行时有效
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface LoggerManage {

    String description();
}
