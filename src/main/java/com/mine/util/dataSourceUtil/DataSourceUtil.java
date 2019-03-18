package com.mine.util.dataSourceUtil;

import lombok.extern.slf4j.Slf4j;

/**
 * Created by jiangqd on 2019/1/21.
 */
@Slf4j
public class DataSourceUtil {

    private static final ThreadLocal<String> contextHolder = new ThreadLocal<>();

    // 设置数据源名
    public static void setDB(String dbType) {
//        log.info("设置为" + dbType + "数据源");
        contextHolder.set(dbType);
    }

    // 获取数据源
    public static String getDB() {
        return (contextHolder.get());
    }

    // 清除数据源
    public static void clearDB() {
        contextHolder.remove();
    }
}
