package com.mine.util.modeUtil;

/**
 * 饿汉式(两种方式)
 * Created by jiangqd on 2019/3/16.
 */
public class Singleton1 {

    /**
     * 静态常量方式
     */
    private final static Singleton1 instance = new Singleton1();

    /**
     * 静态代码块方式，先声明需要返回的对象，但是不创建，在之后的代码块中创建，其它一样
     */
    /*static{
        instance = new Singleton1();
    }*/

    private Singleton1() {
    }

    public static Singleton1 getInstance() {
        return instance;
    }

}
