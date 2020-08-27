package com.mine.common.pagination;

import java.sql.Connection;

import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.plugin.Intercepts;
import org.apache.ibatis.plugin.Signature;
import org.apache.ibatis.session.RowBounds;

/**
 * MySQL 分页拦截器
 * 
 * @author IceLiang
 * @Description 
 * @version V1.0
 * @Date 2019年8月5日 下午6:18:54 
 * @Copyright Copyright © 2019 深圳花儿绽放网络科技股份有限公司. All rights reserved.
 */
@Intercepts({ @Signature(type = StatementHandler.class, method = "prepare", args = { Connection.class, Integer.class }) })
public class MySQLPaginationInterceptor extends AbstractPaginationInterceptor {

	@Override
	public String toPaginationSQL(String originalSql, RowBounds rowBounds) {
		if (originalSql == null || originalSql.equals("")) {
			return originalSql;
		}
		if (originalSql.contains(" limit ")) {
			logger.warn("sql: " + originalSql + " contains 'limit'");
		}
		originalSql = toLineSql(originalSql);
		return originalSql + " limit " + rowBounds.getOffset() + " ,"
		        + rowBounds.getLimit();
	}
}
