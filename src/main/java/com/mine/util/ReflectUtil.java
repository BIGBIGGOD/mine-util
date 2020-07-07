package com.mine.util;

import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodType;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

import org.apache.commons.lang.StringUtils;

import com.mine.entity.User;

/**
 * @author jiangqingdong
 * @version v1.0
 * @Description
 * @DATE 2020/3/9 15:10
 * @Copyright Copyright © 2019 深圳花儿绽放网络科技股份有限公司. All rights reserved.
 */
public class ReflectUtil {

    public static void main(String[] args) throws ClassNotFoundException, NoSuchMethodException, NoSuchFieldException {

        System.out.println("你好啊");
    }

    /**
     * 方法句柄的创建与操作
     */
    public void methodHandle() throws Throwable {
        //生成lookeup查找对象
        MethodHandles.Lookup lookup = MethodHandles.lookup();
        //定义方法信息（返回类型必须指定返回类型且排第一个，返回为null的时候，写为void.class，其后才是各种参数类型，如int.class）
        //其中methodType在生成之后可以通过appendParameterTypes、insertParameterTypes等api再次修改，
        //特别需要注意的是每次修改之后是创建了一个新的methodType实例，需要重新赋值进行操作
        MethodType methodType = MethodType.methodType(String.class, int.class, int.class);
        //通过lookup的findVirtual查找生成对应方法句柄,其中第一个参数为类名，第二个为方法名
        //其中对于用invokestatic调用的静态方法，我们需要使用Lookup.findStatic方法；
        //对于用invokevirtual调用的实例方法，以及用invokeinterface调用的接口方法，我们需要使用 findVirtual 方法；
        //对于用invokespecial调用的实例方法，我们则需要使用findSpecial方法。
        MethodHandle methodHandle = lookup.findVirtual(String.class, "substring", methodType);
        //使用方法句柄以及传入的参数直接执行对应方法,如果去掉其中的（String）强转程序会报错，程序根据强转在内部实现中判断返回类型是否符合预期从而输出结果
        String str = (String) methodHandle.invokeExact("hello, world", 1, 2);
    }

    public void reflect() throws ClassNotFoundException, NoSuchMethodException, NoSuchFieldException, IllegalAccessException {
        Class<?> clazz1 = Class.forName("com.mine.entity.User");
        User user = new User();
        Class<?> clazz2 = user.getClass();
        //获取指定方法
        Method method = clazz2.getMethod("say4", Integer.class);
        //获取所有方法
        Method[] methods = clazz2.getMethods();
        //获取指定属性
        Field field = clazz2.getDeclaredField("name");
        //获取所有属性
        Field[] fields = clazz2.getDeclaredFields();
        //获取当前类加载器
        ClassLoader classLoader = clazz2.getClassLoader();
        //获取对应参数类型的构造器
        Constructor<User> constructor = (Constructor<User>) clazz2.getConstructor(String.class);
        //当实体类的字段或者方法设置为private类型时，反射没有权限获取其属性、方法等信息，需要做例如下面操作获取对应属性的权限
        field.setAccessible(true);
        //反射获取所有属性的时候会包含一些非自定义属性的信息，如下
        if (StringUtils.containsIgnoreCase(field.getName(), "serialVersion")) {
            System.out.println("非自定义属性字段");
        }
        //赋予对应属性值
        field.set(user, "xxx");
        //获取对应属性值
        Object name = field.get(user);
        System.out.println("你好啊");
    }
}
