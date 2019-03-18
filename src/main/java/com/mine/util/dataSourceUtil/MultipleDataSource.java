package com.mine.util.dataSourceUtil;

import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

/**
 * @Description 继承抽象路由数据源类
 * Created by jiangqd on 2019/1/21.
 */
@Slf4j
public class MultipleDataSource extends AbstractRoutingDataSource {

    @Override
    protected Object determineCurrentLookupKey() {
        log.info("切换为" + DataSourceUtil.getDB() + "数据源");
        return DataSourceUtil.getDB();
    }
}

