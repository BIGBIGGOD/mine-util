package com.mine.utils.modeUtil;

/**
 * 懒汉式
 * Created by jiangqd on 2019/3/16.
 */
public class Singleton2 {

    private static Singleton2 instance;

    private Singleton2() {
    }

    public static Singleton2 getInstance() {
        if (instance == null) {
            instance = new Singleton2();
        }
        return instance;
    }

}
