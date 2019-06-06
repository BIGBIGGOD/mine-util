package com.mine.util.testUtil;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.Data;

import java.lang.reflect.Array;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Created by jiangqd on 2019/3/16.
 */
@Data
public class Test1 {

    public static void main(String[] args) throws ClassNotFoundException {
        //查看当前系统类路径中包含的路径条目
//        System.out.println("路径" + System.getProperty("java.class.path"));
        //调用当前类的类加载器
//        Class typeLoaded = Class.forName("com.mine.util.testUtil.Test1");
        //查看被加载的TestBean是被哪个类加载器加载的
//        System.out.println(typeLoaded.getClassLoader());
//        String repeatTempleteText = "@[受邀者昵称]，你已经参与过该活动，不可重复助力快来生成海报，";
//        repeatTempleteText =repeatTempleteText.replace("[受邀者昵称]", "悟空");
//        System.out.println(repeatTempleteText);
        List<String> list= new ArrayList<>();
        List<String> list2= new ArrayList<>();
        list.add("1");
        list2.add("1");
        list2.add("1");
        list.add("2");
        list.add("3");
        list.add("4");
//        list.addAll(list2);
        list.stream().sorted( (s1, s2) -> Integer.valueOf(s2) - Integer.valueOf(s1)).forEach(System.out::println);
        list2 =list.stream().filter(str -> Integer.valueOf(str) > 2).collect(Collectors.toList());
        System.out.println(list);
        System.out.println(list2);
    }

}
