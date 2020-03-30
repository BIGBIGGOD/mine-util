package com.mine.util;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

import com.mine.model.User;

/**
 * @author jiangqingdong
 * @version v1.0
 * @Description
 * @DATE 2020/3/9 15:10
 * @Copyright Copyright © 2019 深圳花儿绽放网络科技股份有限公司. All rights reserved.
 */
public class ReflectUtil {

    public static void main(String[] args) throws ClassNotFoundException, NoSuchMethodException, NoSuchFieldException {
        User user1 = new User();
        Class<?> user2 = Class.forName("com.mine.model.User");
        Method method = user2.getMethod("say4", Integer.class);
        Method[] methods = user2.getMethods();
        Field field = user2.getDeclaredField("name");
        Field[] fields = user2.getDeclaredFields();
        ClassLoader classLoader = user2.getClassLoader();
        Constructor<User> constructor = (Constructor<User>) user2.getConstructor(String.class);

        System.out.println("你好啊");
    }
}
