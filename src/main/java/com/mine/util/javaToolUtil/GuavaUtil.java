package com.mine.util.javaToolUtil;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @author jiangqingdong
 * @version v1.0
 * @Description Guava工具的使用
 * @DATE 2019/7/30 11:29
 * @Copyright Copyright © 2019 深圳花儿绽放网络科技股份有限公司. All rights reserved.
 */
public class GuavaUtil {

    /**
     * 快速创建集合
     */
    public static void testCreateCollections() {
        List<String> list = Lists.newArrayList();
        Map<String, Object> map = Maps.newHashMap();
        Set<String> set = Sets.newHashSet();
    }

    public static void main(String[] args) {
        List<String> list = null;
        for(String str : list) {
            System.out.println(str);
        }
    }
}
