package com.mine.util.testUtil;

import lombok.Data;

/**
 * Created by jiangqd on 2019/3/16.
 */
@Data
public class Test1 {

    public static void main(String[] args) throws ClassNotFoundException {
        //查看当前系统类路径中包含的路径条目
        System.out.println("路径" + System.getProperty("java.class.path"));
        //调用当前类的类加载器
        Class typeLoaded = Class.forName("com.mine.util.testUtil.Test1");
        //查看被加载的TestBean是被哪个类加载器加载的
        System.out.println(typeLoaded.getClassLoader());
    }

}
