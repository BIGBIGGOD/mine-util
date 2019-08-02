package com.mine.util.cacheUtil;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import lombok.extern.slf4j.Slf4j;
import org.ansj.domain.Result;
import org.ansj.domain.Term;
import org.ansj.splitWord.analysis.IndexAnalysis;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;

/**
 * @author jiangqingdong
 * @version v1.0
 * @Description 使用Guava做本地缓存
 * @DATE 2019/7/29 17:55
 * @Copyright Copyright © 2019 深圳花儿绽放网络科技股份有限公司. All rights reserved.
 */
@Slf4j
public class LocalCacheUtil {


    private Cache<String, List<Integer>> listLocalCache = CacheBuilder.newBuilder()
            .maximumSize(5000)
            .recordStats()
            .build();

    /**
     * 刷新本地缓存
     *      - 清除当前缓存
     *      - 重新初始化
     */
    public void flushLocalCache() {
        listLocalCache.invalidateAll();

        // 刷新后并执行初始化操作（重新读取，架子啊）
        this.initCache();
    }

    /**
     * 初始化本地缓存
     *
     * TODO 【注意】若后期数据过多，需要考虑 Tomcat 的内存支撑
     * 注解@PostConstruct表示在项目构建的时候就会执行
     */
    @PostConstruct
    public void initCache() {
        long startTime = System.currentTimeMillis();
        log.info("【本地缓存-数据加载】 >>>> 开始 >>>>");

        //可利用之前定义的本地缓存类型或者格式存储和获取对象
        List<Integer> list = new ArrayList();
        listLocalCache.put("key", list);
        List<Integer> newList = listLocalCache.getIfPresent("key");

        log.info("【本地缓存-数据加载】 <<<< 结束（共使用: {} ms） <<<<", System.currentTimeMillis() - startTime);
    }
}
