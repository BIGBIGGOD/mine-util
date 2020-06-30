
package com.mine.util.shardingutil;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;

import com.alibaba.druid.pool.DruidDataSource;

/**
 * sharding-jdbc测试类，未编写完成，可删除
 */
public class Test {

    @Value("${mybatis.jdbcUrl}")
    private String dbUrl;

    @Value("${mybatis.username}")
    private String username;

    @Value("${mybatis.password}")
    private String password;

    @Value("${mybatis.driverClass}")
    private String driverClassName;

    @Value("${mybatis.initialSize}")
    private int initialSize;

    @Value("${mybatis.maxActive}")
    private int maxActive;

    @Value("${mybatis.maxIdle}")
    private String maxIdle;

    @Value("${mybatis.minIdle}")
    private int minIdle;

    @Value("${mybatis.maxWait}")
    private long maxWait;

    /**
     * 配置第一个数据源
     *
     * @return
     */
    @Bean(name = "dataSource1")
    public DruidDataSource druidDataSource() {
        DruidDataSource dataSource = new DruidDataSource();
        dataSource.setUsername(username);
        dataSource.setPassword(password);
        dataSource.setUrl(dbUrl);
        dataSource.setDriverClassName(driverClassName);
        dataSource.setInitialSize(initialSize);
        dataSource.setMaxActive(maxActive);
        dataSource.setMinIdle(minIdle);
        dataSource.setMaxWait(maxWait);
        return dataSource;
    }

}
