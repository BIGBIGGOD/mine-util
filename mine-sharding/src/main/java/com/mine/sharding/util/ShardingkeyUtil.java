package com.mine.sharding.util;

/**
 * 分表的工具类
 *
 * @author IceLiang
 * @version V1.0
 * @Description
 * @Date 2019年11月20日 上午11:03:35
 * @Copyright Copyright © 2019 深圳花儿绽放网络科技股份有限公司. All rights reserved.
 */
public class ShardingkeyUtil {
    public static final Integer[] ARRAY = {1,2,3,4,5};

    public static void main(String[] args) {
        String gid = "5555";
        System.out.println(getIndex(gid));
        int hashCode = HashUtil.getHash(gid);
        int index = Math.abs(hashCode % ARRAY.length);
        System.out.println("表的位置为:" + ARRAY[index]);
    }

    public static int getIndex(String openid) {
        int hashCode = HashUtil.getHash(openid);
        int index = Math.abs(hashCode % ARRAY.length);
        return ARRAY[index];
    }
}
