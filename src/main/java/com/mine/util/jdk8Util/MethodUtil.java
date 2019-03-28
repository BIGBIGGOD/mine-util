package com.mine.util.jdk8Util;

import com.google.common.base.Predicates;
import com.mine.model.User;

import java.util.function.Supplier;

/**
 * 方法和构造函数引用,其实还是另类的实现接口方法输出
 * Created by jiangqd on 2019/3/21.
 */
public class MethodUtil {

    public static void main(String[] args) {
//        method1();
//        method2();
//        method3();
        new MethodUtil().method4();
    }

    /**
     * 注意方法引用的时候的格式，不需要显示参数
     */
    public static void method1() {
        InterfaceUtil<String, Integer> interfaceUtil = Integer::valueOf;
        int i = 22;
        interfaceUtil.sayHello(i);
        i++;
        System.out.println(i);

    }

    /**
     * 构造函数引用
     */
    public static void method2() {
        User user = new User();
        InterfaceUtil<String, Integer> interfaceUtil = user::say4;
        interfaceUtil.sayHello(5641);
    }

    /**
     * 局部变量,lambda引用的方法参数json在之后可以改变，但是在方法内部引用的参数s默认是隐含的final变量无法改变了
     */
    public static void method3() {
        String s = "555";
        String json = "woshi";
        InterfaceUtil<String, String> interfaceUtil = str -> {
            System.out.println(str);
            if (str.equals("1")) {
                System.out.println("你好啊" + s);
            } else {
                System.out.println("不好" + s);
            }
        };
        interfaceUtil.sayHello(json);
//        s = "aiaiaiai";
        System.out.println(s);
    }

    /**
     * 成员变量与静态变量,这两种变量在引用之后不是final类型的
     */
    String str1;
    static String str2;

    public void method4() {
        str1 = "ksdjf";
        str2 = "klaoiennvv";
        InterfaceUtil<String, String> interfaceUtil = str -> System.out.println(str1 + str+str2);
        interfaceUtil.sayHello("iiiiii");
        str1 = "jkj";
        System.out.println(str1);
        str2 = "aaaa";
        System.out.println(str2);
    }

    public static void method5() {


    }

}
