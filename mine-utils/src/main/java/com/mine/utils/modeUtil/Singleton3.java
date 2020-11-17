package com.mine.utils.modeUtil;

/**
 * 利用枚举建立单例模式，枚举天生就是单例
 * Created by jiangqd on 2019/3/16.
 */
public enum Singleton3 {
    instance(1, "悟空");
    private int id;
    private String name;

    public int getId() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }

    private Singleton3(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public Singleton3 getInstance(int id) {
        for (Singleton3 singleton : values()) {
            if (singleton.getId() == id) {
                return singleton;
            }
        }
        return null;
    }

}
