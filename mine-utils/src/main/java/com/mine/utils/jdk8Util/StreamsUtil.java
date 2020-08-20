package com.mine.utils.jdk8Util;

import java.util.*;
import java.util.stream.Collectors;

import org.springframework.util.StopWatch;

import com.mine.common.entity.User;
import lombok.extern.slf4j.Slf4j;

/**
 * Streams(管道)
 * Created by jiangqd on 2019/3/22.
 */
@Slf4j
public class StreamsUtil {

    public static void main(String[] args) {
        forCompare();
//        List<String> stringCollection = new ArrayList<>();
//        stringCollection.add("ddd2");
//        stringCollection.add("aaa2");
//        stringCollection.add("bbb1");
//        stringCollection.add("aaa1");
//        stringCollection.add("bbb3");
//        stringCollection.add("ccc9");
//        stringCollection.add("bbb2");
//        stringCollection.add("ddd1");
//
//        method1(stringCollection);
//        method1(stringCollection);
    }

    /**
     * 测试stream的基本函数使用
     *
     * @param list
     */
    public static void method1(List<String> list) {
        System.out.println("filter对集合做条件过滤");
        list.stream().filter(str -> str.startsWith("b")).forEach(System.out::println);
        System.out.println("sorted对集合做自然排序");
        list.stream().filter(str -> str.startsWith("b")).sorted().forEach(System.out::println);
        System.out.println("sorted对集合做自定义排序");
        //当s1-s2的时候默认是从小到大，反之则相反
        Comparator<String> comparator = (s1, s2) -> Integer.valueOf(s2.substring(s2.length() - 1)) - Integer.valueOf(s1.substring(s1.length() - 1));
        list.stream().sorted(comparator).forEach(System.out::println);
        System.out.println("map对集合每个元素做中间层处理");
        list.stream().sorted(comparator).map(String::toUpperCase).forEach(System.out::println);
        System.out.println("match对集合做条件判断返回布尔结果,anyMatch(部分符合条件）、allMatch（全符合条件）、noneMatch（全不符合条件");
        System.out.println(list.stream().anyMatch(s -> s.startsWith("a")));
        System.out.println(list.stream().allMatch(s -> s.startsWith("a")));
        System.out.println(list.stream().noneMatch(s -> s.startsWith("q")));
        System.out.println("count返回流中元素数目");
        System.out.println(list.stream().count());
        System.out.println("reduce通过制定的方法对元素进行削减操作");
        Optional<String> optional = list.stream().reduce((s1, s2) -> s1 + "#" + s2);
        System.out.println(optional.get());
    }

    /**
     * stream（连续型）与parallelStream（并行）提高性能,但是线程不安全
     */
    public static void method2() {
        int max = 1999999;
        List<String> values = new ArrayList<>(max);
        for (int i = 0; i < max; i++) {
            UUID uuid = UUID.randomUUID();
            values.add(uuid.toString());
        }
        StopWatch stopWatch = new StopWatch();
        long time1 = System.currentTimeMillis();
        stopWatch.start();
        values.stream().sorted();
        stopWatch.stop();
        long time2 = System.currentTimeMillis();
        System.out.println("使用stream时耗时time=" + (time2 - time1));
        System.out.println("使用stream时耗时time=" + stopWatch.shortSummary());
        stopWatch.start();
        values.parallelStream().sorted();
        stopWatch.stop();
        System.out.println("使用stream时耗时time=" + stopWatch.prettyPrint());
    }

    /**
     * map的基本使用
     */
    public static void method3() {
        Map<Integer, String> map = new HashMap<>();
        for (int i = 0; i < 10; i++) {
            //putIfAbsent表示如果key存在则不插入数据，之后返回value
            map.putIfAbsent(i, "val" + i);
        }
        map.forEach((id, val) -> System.out.println(val));
    }

    /**
     * stream返回list的方法
     */
    public static void method4(List<String> list) {

        System.out.println("使用 Collectors.toList 方法");
        List<String> list1 = list.stream().filter(s -> s.startsWith("b")).collect(Collectors.toList());
        System.out.println(list1);
        System.out.println("使用 toCollection() 方法 ");
        List<String> list2 = list.stream().collect(Collectors.toCollection(ArrayList::new));
        System.out.println(list2);
        System.out.println("使用 forEach() 方法  ");
        List<String> list3 = new ArrayList<>();
//        list.stream().forEachOrdered(list3::add);
        list.stream().forEach(s -> list3.add(s));
        list.stream().forEach(list3::add);
        System.out.println(list3);
        User user = new User();
    }

    /**
     * 遍历方法比较
     */
    public static void forCompare() {
        List<Integer> list = new ArrayList<>();
        StopWatch stopWatch = new StopWatch();
        stopWatch.start("任务一");
        for (int i = 0; i < 99999; i++) {
            list.add(i);
        }
        stopWatch.stop();
        System.out.println("加载时间:" + stopWatch.getLastTaskTimeMillis());
        stopWatch.start("任务二");
        list.forEach(System.out::print);
        System.out.println();
        stopWatch.stop();
        System.out.println("普通foreach时间:" + stopWatch.getLastTaskTimeMillis());
        stopWatch.start("任务三");
        list.stream().forEach(System.out::print);
        System.out.println();
        stopWatch.stop();
        System.out.println("stream的foreach时间:" + stopWatch.getLastTaskTimeMillis());
        System.out.println("总时间"+stopWatch.prettyPrint());
    }

}
