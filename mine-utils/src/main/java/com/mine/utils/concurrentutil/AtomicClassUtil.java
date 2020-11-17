package com.mine.utils.concurrentutil;

import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReferenceArray;
import java.util.concurrent.atomic.AtomicReferenceFieldUpdater;

/**
 * java中13个原子操作类总结
 * Created by jiangqd on 2019/3/26.
 */
public class AtomicClassUtil {

    public static void main(String[] args) {
        method3();
    }

    /**
     * 原子更新基本类型(3种):AtomicBoolean、AtomicInteger、AtomicLong
     */
    public static void method1() {
        AtomicBoolean a1 = new AtomicBoolean();
        a1.set(true);
        // 如果输入的值等于预期值，则以原子方式将该值设置为输入的值
        a1.compareAndSet(true, false);
        System.out.println("a2=" + a1);
        AtomicInteger a2 = new AtomicInteger();
        //以原子的方式将输入的数值与实例中的值相加，并返回结果
        a2.addAndGet(2);
        //以原子的方式设置为newValue,并返回旧值
        a2.getAndAdd(5);
        // 以原子的方式将当前值加1，注意，这里返回的是自增前的值
        a2.getAndIncrement();
        //最终会设置成newValue,使用lazySet设置值后，可能导致其他线程在之后的一小段时间内还是可以读到旧的值
        a2.lazySet(55);
        System.out.println("a2=" + a2);
    }

    /**
     * 原子更新数组(3种):AtomicIntegerArray、AtomicLongArray、AtomicReferenceArray
     */
    public static void method2() {
        String[] arr = new String[]{"sdfs", "df", "mxn./", "z@%@#"};
        AtomicReferenceArray<String> atomicReferenceArray = new AtomicReferenceArray<>(arr);
        // 同基本类型一样，如果输入的值等于预期值，则以原子方式将该值设置为输入的值
        atomicReferenceArray.compareAndSet(0, "sdfs", "index0");
        // 获取指定下标位置的值
        System.out.println(atomicReferenceArray.get(0));
        System.out.println(arr[0]);
    }

    /**
     * 原子更新引用类型(三种)：AtomicReference（原子更新引用类型）、AtomicReferenceFieldUpdater（原子更新引用类型的字段）、AtomicMarkableReferce（原子更新带有标记位的引用类型）
     */
    public static void method3() {

        AtomicReferenceFieldUpdater<Ajkjij, String> atomicReferenceFieldUpdater = AtomicReferenceFieldUpdater.newUpdater(Ajkjij.class, String.class, "status");
        Ajkjij a = new Ajkjij();
        //注意是字段值而不是字段名
        atomicReferenceFieldUpdater.compareAndSet(a, "11", "222");
        System.out.println(a.status);
    }

    static class Ajkjij {

        public volatile String status = "11";

    }

    /**
     * 原子更新字段类型：
     * AtomicIntegerFieldUpdater: 原子更新整型的字段的更新器
     * AtomicLongFieldUpdater: 原子更新长整型字段的更新器
     * AtomicStampedFieldUpdater: 原子更新带有版本号的引用类型。
     * AtomicReferenceFieldUpdater同上
     */
    public static void method4() {
        //演示同原子引用类型
    }
}
