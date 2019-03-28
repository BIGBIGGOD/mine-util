package com.mine.util.jdk8Util;

/**
 * 函数式接口，利用lambda表达式实现接口，有对应测试方法
 * Created by jiangqd on 2019/3/21.
 */
public interface InterfaceUtil<F, T> {

    void sayHello(T i);

    /**
     * 默认方法，可以直接new 接口调用
     */
    default void sayHi() {
        System.out.println("hi,你好啊");
    }

    /**
     * 静态方法，可以直接用接口名调用
     */
    static void sayHi2() {
        System.out.println("hi,你好啊");
    }

}
