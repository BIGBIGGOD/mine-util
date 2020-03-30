package com.mine.filter;

import com.google.common.hash.BloomFilter;
import com.google.common.hash.Funnels;

/**
 * @author jiangqingdong
 * @version v1.0
 * @Description 布隆过滤器使用，存在误差，能判定一个数肯定不存在，或者说一个数可能存在
 * 一个空的布隆过滤器是一个m位的位数组，所有位的值都为0。
 * 定义了k个不同的符合均匀随机分布的哈希函数，每个函数把集合元素映射到位数组的m位中的某一位。
 * @DATE 2020/3/30 9:39
 * @Copyright Copyright © 2019 深圳花儿绽放网络科技股份有限公司. All rights reserved.
 */
public class BloomFilterUtil {


    public static void main(String[] args) {
        testBloomFilterByGuava();
    }

    public static void testBloomFilterByGuava() {
        int size = 1000000;
        double fpp = 0.01;

        /**
         * Funnels指定数据类型，这里用的是Integer
         * size指预计要插入多少数据
         * fpp指期望的误判率
         * Strategy指哈希算法
         */
        BloomFilter<Integer> bloomFilter = BloomFilter.create(Funnels.integerFunnel(), size, fpp);
        //插入数据
        for (int i = 0; i < 1000000; i++) {
            bloomFilter.put(i);
        }
        int count = 0;
        for (int i = 1000000; i < 2000000; i++) {
            if (bloomFilter.mightContain(i)) {
                count++;
                System.out.println(i + "误判了");
            }
        }
        System.out.println("总共的误判数:" + count);
        bloomFilter.put(58546);
    }
}
