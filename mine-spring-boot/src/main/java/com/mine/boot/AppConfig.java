package com.mine.boot;

import com.alibaba.druid.pool.DruidDataSource;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.*;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import javax.sql.DataSource;

/**
 * @Description: spring与mybatis整合类
 * Created by jiangqd on 2019/1/12.
 */

/**
 * @Configuration 注解标注该类为注解配置类
 */
//@Configuration
/**
 * @ComponentScan是spring的注解，扫描指定路径中的注解
 * 自定扫描路径下边带有@Controller，@Service，@Repository，@Component注解加入spring容器
 * 通过includeFilters加入扫描路径下没有以上注解的类加入spring容器
 * 通过excludeFilters过滤出不用加入spring容器的类
 */
//@ComponentScan(basePackages = {"com.mine"},excludeFilters = {@ComponentScan.Filter(type = FilterType.ANNOTATION,value = EnableWebMvc.class)})

/**
 * @MapperScan是mybatis的注解，扫描指定路径中的mapper
 * 当含有多个扫描路径时，用花括号括起来，路径间用逗号隔开
 */
//@MapperScan({"com.mine"})

//@PropertySource("classpath:/application.properties")

/**
 * @author admin
 * @EnableAspectJAutoProxy 注解开启AOP,
 * @EnableTransactionManagement 注解开启spring事务管理,
 * @EnableCaching 注解开启spring缓存
 * @EnableWebMvc 注解开启webMvc
 */
//@EnableAspectJAutoProxy
public class AppConfig {

//    @Value("${mybatis.jdbcUrl}")
//    private String dbUrl;
//
//    @Value("${mybatis.username}")
//    private String username;
//
//    @Value("${mybatis.password}")
//    private String password;
//
//    @Value("${mybatis.driverClass}")
//    private String driverClassName;
//
//    @Value("${mybatis.initialSize}")
//    private int initialSize;
//
//    @Value("${mybatis.maxActive}")
//    private int maxActive;
//
//    @Value("${mybatis.maxIdle}")
//    private String maxIdle;
//
//    @Value("${mybatis.minIdle}")
//    private int minIdle;
//
//    @Value("${mybatis.maxWait}")
//    private long maxWait;

//    @Bean
//    @Autowired
//    public SqlSessionFactoryBean sqlSessionFactoryBean(DataSource dataSource) {
//        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
//        sqlSessionFactoryBean.setDataSource(dataSource);
//        return sqlSessionFactoryBean;
//    }

//    @Bean(name = "dataSource")
//    public DruidDataSource druidDataSource() {
//        DruidDataSource dataSource = new DruidDataSource();
//        dataSource.setUsername(username);
//        dataSource.setPassword(password);
//        dataSource.setUrl(dbUrl);
//        dataSource.setDriverClassName(driverClassName);
//        dataSource.setInitialSize(initialSize);
//        dataSource.setMaxActive(maxActive);
//        dataSource.setMinIdle(minIdle);
//        dataSource.setMaxWait(maxWait);
//        return dataSource;
//    }

    /**
     * 配置文件上传
     * @return
     */
//    @Bean
//    public CommonsMultipartResolver multipartResolver() {
//        CommonsMultipartResolver commonsMultipartResolver = new CommonsMultipartResolver();
//        commonsMultipartResolver.setDefaultEncoding("UTF-8");
//        commonsMultipartResolver.setMaxUploadSize(5400000);
//        return commonsMultipartResolver;
//    }

    /**
     * freemarker配置工厂类，注入模板路径,pom还未配置
     * @return
     */
    //对应的方法名就是bean定义的默认beanName
    /*@Bean
    public FreeMarkerConfigurationFactoryBean freeMarkerConfigurationFactoryBean() {
        FreeMarkerConfigurationFactoryBean freeMarkerConfigurationFactoryBean = new FreeMarkerConfigurationFactoryBean();
        freeMarkerConfigurationFactoryBean.setDefaultEncoding("UTF-8");
        freeMarkerConfigurationFactoryBean.setTemplateLoaderPath("/WEB-INF/ftl/");
        return freeMarkerConfigurationFactoryBean;
    }*/

}
