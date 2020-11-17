package com.mine.utils.dataSourceUtil;

import lombok.extern.slf4j.Slf4j;

/**
 *
 * @author jiangqd
 * @date 2019/1/21
 */
@Slf4j
public class DataSourceUtil {

    private static final ThreadLocal<String> contextHolder = new ThreadLocal<>();

    /**
     * 设置数据源名
     * @param dbType 数据源名称
     */
    public static void setDB(String dbType) {
        log.info("设置为" + dbType + "数据源");
        contextHolder.set(dbType);
    }

    /**
     * 获取数据源
     * @return res
     */
    public static String getDB() {
        return (contextHolder.get());
    }

    /**
     * 清除数据源
     */
    public static void clearDB() {
        contextHolder.remove();
    }
}
