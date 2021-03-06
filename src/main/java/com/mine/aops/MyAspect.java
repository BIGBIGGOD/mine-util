package com.mine.aops;

import com.google.common.base.Joiner;
import lombok.extern.slf4j.Slf4j;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

/**
 * Created by jiangqd on 2019/1/12.
 */
@Slf4j
//@Component
//@Aspect
public class MyAspect {

    private final static char DOT_CHAR = '.';
    //execution表示指定匹配类型方法，使用@表示匹配任何持有该注解的方法
    //within表示匹配指定类型路径下的方法执行，也可以使用@表示匹配使用了指定注解的方法
    //可以使用环绕通知，第一个*表示类型（如public），..表示当前包及子包，第二个*表示类，.*(..)表示任何方法，括号内..表示任意参数
//    @Around("execution(* com.mine..service.*.*(..))")
    @Around("execution(* com.mine..*.*(..))")
    public Object pointCut(ProceedingJoinPoint pjp) throws Throwable {
        //拼接service的方法名称
        //getDeclaringTypeName是一个返回方法所在的包名和类名getName是返回方法名
        String serviceMethodNam = Joiner.on(DOT_CHAR).join(pjp.getSignature().getDeclaringTypeName(), pjp.getSignature().getName());
        //获取参数,实现类的toString方法，还能通过参数设置toString的样式
        String args = ToStringBuilder.reflectionToString(pjp.getArgs(), ToStringStyle.SHORT_PREFIX_STYLE);
//        log.info("Service start: serviceMethodNam={}, args={}", serviceMethodNam, args);

        // stopwatch可以用来计算一段时间，利用getTotalTimeMillis方法得到执行总时间，利用prettyPrint方法查看大致情况
        System.out.println("进入MyAspect");
        StopWatch sw = new StopWatch();
        sw.start(pjp.toShortString());
        Object retVal = null;
        try {
            retVal = pjp.proceed();
            if (null != retVal) {
//                log.info("Service finished: serviceMethodNam={}, result={}", serviceMethodNam,
//                    ToStringBuilder.reflectionToString(retVal, ToStringStyle.JSON_STYLE));
            }
        } catch (Exception e) {
            log.error(pjp.getSignature().getName() + "Error:" + e.getMessage());
        } finally {
            sw.stop();
        }
        // stop stopwatch
        return retVal;
    }

//    //也可以自己定义一个切点实现简单的日志处理
//    @Pointcut("execution(* com.facishare.open..service.*.*(..))")
//    public Object pointCut(ProceedingJoinPoint pjp) {
//
//    }
//
//    @Before("pointCut()")
//    public void before(ProceedingJoinPoint point) {
//        System.out.println(point.getArgs());
//        System.out.println("before");
//    }
//
//    @After("pointCut()")
//    public void after() {
//        System.out.println("after");
//    }

}
