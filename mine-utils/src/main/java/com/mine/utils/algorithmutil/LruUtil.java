package com.mine.utils.algorithmutil;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author jiangqingdong
 * @version v1.0
 * @Description lLRU算法的设计原则是：如果一个数据在最近一段时间没有被访问到，那么在将来它被访问的可能性也很小。也就是说，当限定的空间已存满数据时，应当把最久没有被访问到的数据淘汰。
 * @DATE 2020/6/18 0018 15:11
 * @Copyright Copyright © 2019 深圳花儿绽放网络科技股份有限公司. All rights reserved.
 */
public class LruUtil<K, V> {

    private static final float hashLoadFactory = 0.75f;
    private LinkedHashMap<K, V> map;
    private int cacheSize;

    public LruUtil(int cacheSize) {
        this.cacheSize = cacheSize;
        int capacity = (int) Math.ceil(cacheSize / hashLoadFactory) + 1;
        // 参数说明：
        // initialCapacity   初始容量大小，使用无参构造方法时，此值默认是16
        // loadFactor       加载因子，使用无参构造方法时，此值默认是 0.75f
        // accessOrder   false： 基于插入顺序     true：  基于访问顺序
        map = new LinkedHashMap<K, V>(capacity, hashLoadFactory, true) {
            private static final long serialVersionUID = 1;

            @Override
            protected boolean removeEldestEntry(Map.Entry eldest) {
                return size() > LruUtil.this.cacheSize;
            }
        };
    }

    public synchronized V get(K key) {
        return map.get(key);
    }

    public synchronized void put(K key, V value) {
        map.put(key, value);
    }

    public synchronized void clear() {
        map.clear();
    }

    public synchronized int usedSize() {
        return map.size();
    }

    public void print() {
        for (Map.Entry<K, V> entry : map.entrySet()) {
            System.out.print(entry.getValue() + "--");
        }
        System.out.println();
    }
}
