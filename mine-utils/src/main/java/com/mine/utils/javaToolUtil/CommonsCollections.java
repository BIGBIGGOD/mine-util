package com.mine.utils.javaToolUtil;

import com.google.common.collect.Lists;
import org.apache.commons.collections.CollectionUtils;

import java.util.ArrayList;

/**
 * @Description 集合操作
 * Created by jiangqd on 2019/1/18.
 */
public class CommonsCollections {

    public static  void test() {
        ArrayList list = Lists.newArrayList();
        boolean res1 = CollectionUtils.isEmpty(list);
        boolean res2 = list.isEmpty();
        System.out.println(res1);
    }
    public static void main(String[] args) {

        CommonsCollections.test();

    }
}
