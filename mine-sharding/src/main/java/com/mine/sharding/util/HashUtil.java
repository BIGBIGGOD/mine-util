package com.mine.sharding.util;

import org.apache.commons.lang3.StringUtils;

/**
 * hash计算工具类
 *
 * @author IceLiang
 * @version V1.0
 * @Description
 * @Date 2019年8月8日 下午5:15:03
 * @Copyright Copyright © 2019 深圳花儿绽放网络科技股份有限公司. All rights reserved.
 */
public class HashUtil {
    /**
     * 把一个String计算hash值
     *
     * @param s
     * @return
     */
    public static int getHash(String s) {
        int h = 0;
        if (StringUtils.isBlank(s)) {
            return h;
        }
        for (int i = 0; i < s.length(); i++) {
            h = 31 * h + s.charAt(i);
        }
        return h;
    }

    public static void main(String[] args) {
        String str = "sagdaghfq";
        System.out.println(str.hashCode());
        System.out.println(HashUtil.getHash(str));
    }

}
