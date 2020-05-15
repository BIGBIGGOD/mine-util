package com.mine.util.javaToolUtil;

import java.util.*;

import com.google.common.base.*;
import com.google.common.collect.*;

/**
 * @author jiangqingdong
 * @version v1.0
 * @Description Guava工具中集合的使用
 * @DATE 2019/7/30 11:29
 * @Copyright Copyright © 2019 深圳花儿绽放网络科技股份有限公司. All rights reserved.
 */
public class GuavaCollectionsUtil {

    /**
     * 普通集合的创建
     */
    public static void createOrdinaryCollections() {
        List<String> list = Lists.newArrayList();
        Map<String, Object> map = Maps.newHashMap();
        Set<String> set = Sets.newHashSet();
    }

    /**
     * 不变集合的创建
     * 在多线程操作下，是线程安全的
     * 所有不可变集合会比可变集合更有效的利用资源
     * 中途不可改变
     */
    public static void createUnchangedCollections() {
        ImmutableList<String> list = ImmutableList.of("a", "b", "c");
        ImmutableSet<String> set = ImmutableSet.of("a", "b", "c");
        ImmutableMap<String, Object> map = ImmutableMap.of("k1", "v2", "k2", "v2");
    }

    /**
     * guava中的集合黑科技
     */
    public static void blackTechCollections() {
        //需要一个map中包含key为String类型，value为List类型的时候,普通创建为：Map<String, List<String>> map = Maps.newHashMap(),后续需要创建List对象一个一个的put放进map中
        Multimap<String, Integer> map = ArrayListMultimap.create();
        Multimap<String, Integer> map1 = LinkedHashMultimap.create();
        map.put("xx", 1);
        map.put("xx", 2);
        map.put("x", 2);
        map.put("x", 3);
        //Multimap: key-value  key可以重复,根据key获取到的是一个集合
        Multimap<String, String> testMap = ArrayListMultimap.create();
        testMap.put("x", "s");
        testMap.put("x", "ss");
        testMap.get("x");
        //Multiset中无序+可重复   count()方法获取单词的次数,移除某个元素的时候只会移除一个，  增强了可读性+操作简单
        Multiset<String> set = TreeMultiset.create();
        Multiset<String> set1 = HashMultiset.create();
        set.add("x");
        set.add("x");
        set.count("x");
        set.add("xx");
        set.remove("x");
        //BiMap: 双向Map(Bidirectional Map) 键与值都不能重复,键重复时会把之前的value覆盖掉，而value重复时会异常
        BiMap<String, String> biMap = HashBiMap.create();
        biMap.put("x", "s");
        biMap.put("x", "ss");
        //Table： 相当于双键的Map，结构为Table-->rowKey+columnKey+value，和sql中的联合主键有点像，同样的key会导致value被覆盖
        Table<String, String, Integer> table = HashBasedTable.create();
        table.put("x", "s", 1);
        table.put("x", "s", 2);
        Integer res = table.get("x", "s");
    }

    /**
     * 集合转换为特定规则的字符串
     */
    public static void transformCollectionsT0String() {
        //List转换为特定的字符串
        List<String> list = ImmutableList.of("x", "xx", "xxx");
        List<String> newList = Lists.newArrayList("x", "xx", "xx");
        String str1 = Joiner.on("-").join(list);
        String str2 = Joiner.on("000").join(newList);
        //map集合转换为特定规则的字符串
        Map<String, String> map = Maps.newHashMap();
        map.put("x", "s");
        map.put("xx", "ss");
        ImmutableMap<String, String> newMap = ImmutableMap.of("x", "s", "xx", "ss");
        String str3 = Joiner.on(",").withKeyValueSeparator("=").join(map);
        String str4 = Joiner.on(",").withKeyValueSeparator("=").join(newMap);
        System.out.println("你哈啊");
    }

    /**
     * 字符串转换为特定规则的集合
     */
    public static void transformStringT0Collections() {
        //String转List，使用omitEmptyStrings().trimResults()去除空字符串和空格
        String str1 = "1-2-3-4-5-6";
        List<String> list = Splitter.on("-").omitEmptyStrings().trimResults().splitToList(str1);
        //String转map
        String str2 = "xiaoming=11,xiaohong=23";
        Map<String, String> map = Splitter.on(",").withKeyValueSeparator("=").split(str2);
        System.out.println("你哈啊");
    }

    /**
     * guava中的多字符串或者特定正则分割
     */
    public static void splitString() {
        //String转List
        String str1 = "1-2-3-4-5-6";
        List<String> list = Splitter.on("-").splitToList(str1);
        // 判断匹配结果
        boolean result = CharMatcher.inRange('a', 'z').or(CharMatcher.inRange('A', 'Z')).matches('0');

        // 保留符合条件数字文本  CharMatcher.digit() 已过时   retain 保留
        String s1 = CharMatcher.inRange('0', '2').retainFrom("abc 123 efg");

        // 删除符合条件数字文本  remove 删除
        String s2 = CharMatcher.inRange('0', '2').removeFrom("abc 123 efg");
        System.out.println("你哈啊");
    }

    /**
     *  集合的过滤
     */
    public static void filterColletions() {
        //按照条件过滤
        ImmutableList<String> names = ImmutableList.of("begin", "code", "Guava", "Java");
        Iterable<String> fitered = Iterables.filter(names, Predicates.or(Predicates.equalTo("Guava"), Predicates.equalTo("Java")));
        System.out.println(fitered);
        //自定义过滤条件   使用自定义回调方法对Map的每个Value进行操作
        ImmutableMap<String, Integer> m = ImmutableMap.of("begin", 12, "code", 15);
        // Function<F, T> F表示apply()方法input的类型，T表示apply()方法返回类型
        Map<String, Integer> m2 = Maps.transformValues(m, new Function<Integer, Integer>() {
            @Override
            public Integer apply(Integer input) {
                if (input > 12) {
                    return input;
                } else {
                    return input + 1;
                }
            }
        });
        System.out.println(m2);
    }

    /**
     * set集合的交集, 并集, 差集
     */
    public static void setCollectionsOperation() {
        HashSet<Integer> setA = Sets.newHashSet(1, 2, 3, 4, 5);
        HashSet<Integer> setB = Sets.newHashSet(4, 5, 6, 7, 8);

        Sets.SetView union = Sets.union(setA, setB);
        System.out.println("union:");
        for (Object integer : union) {
            System.out.println(integer);
        }

        Sets.SetView difference = Sets.difference(setA, setB);
        System.out.println("difference:");
        for (Object integer : difference) {
            System.out.println(integer);
        }

        Sets.SetView intersection = Sets.intersection(setA, setB);
        System.out.println("intersection:");
        for (Object integer : intersection) {
            System.out.println(integer);
        }
    }

    /**
     * map集合的交集, 并集, 差集
     */
    public static void mapCollectionsOperation() {
        HashMap<String, Integer> mapA = Maps.newHashMap();
        mapA.put("a", 1);
        mapA.put("b", 2);
        mapA.put("c", 3);

        HashMap<String, Integer> mapB = Maps.newHashMap();
        mapB.put("b", 20);
        mapB.put("c", 3);
        mapB.put("d", 4);
        //获取到两个map对比的所有的信息
        MapDifference differenceMap = Maps.difference(mapA, mapB);
        //判断对比信息是否为空
        differenceMap.areEqual();
        //获取两个map的不同点
        Map entriesDiffering = differenceMap.entriesDiffering();
        //获取左边map独有的信息
        Map entriesOnlyLeft = differenceMap.entriesOnlyOnLeft();
        //获取右边map独有的信息
        Map entriesOnlyRight = differenceMap.entriesOnlyOnRight();
        //获取两个map的相同点
        Map entriesInCommon = differenceMap.entriesInCommon();
    }

    public static void main(String[] args) {
        mapCollectionsOperation();
    }
}
