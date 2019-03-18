package com.mine.test;

import com.mine.model.User;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.*;

/**
 * Created by jiangqd on 2019/3/16.
 */
@Slf4j
public class CollectionsTest {

    //数组复制
    @Test
    public void test1() {
        User[] users = new User[]{new User()};
        System.out.println(Arrays.toString(users));
        int[] elementData = new int[]{1, 9, 5, 4};
        System.arraycopy(elementData, 0, elementData, 0, elementData.length);
        System.out.println(Arrays.toString(elementData));
    }

    //集合迭代器
    @Test
    public void test2() {
        List<String> list = new ArrayList<>();
        list.add("1");
        list.add("2");
        list.add("3");
        Iterator<String> iterator = list.iterator();
        while (iterator.hasNext()) {
            String str = iterator.next();
            iterator.remove();
        }
    }

    //计算重复的字符串个数
    @Test
    public void test3() {
        String str = "khjgifrhgs，，，，，。。。。j士大夫knhrjfsa";
        char[] arr = str.toCharArray();
        Map<String, Integer> map = new HashMap<>();
        Integer count = 0;
        for (char c : arr) {
            if (map.containsKey(String.valueOf(c))) {
                count = map.get(String.valueOf(c)) + 1;
                map.put(String.valueOf(c), count);
            } else {
                map.put(String.valueOf(c), 1);
            }
        }
        System.out.println(map);
    }

    //keySet为返回map集合的key集合，是一个set类型的集合
    @Test
    public void test4() {
        Map<String, String> map = new HashMap<>();
        map.put("1", "jifo");
        map.put("2", "jifo");
        map.put("3", "jifo");
        map.put("4", "jifo");
        map.put("5", "jifo");
        Set<String> set = map.keySet();
        System.out.println(set);
    }

    /**
     * 反射使用
     * getField 只能获取public的，包括从父类继承来的字段。
     * getDeclaredField 可以获取本类所有的字段
     *
     * @throws ClassNotFoundException
     * @throws IllegalAccessException
     * @throws InvocationTargetException
     */
    @Test
    public void test5() throws ClassNotFoundException, IllegalAccessException, InvocationTargetException, NoSuchMethodException, InstantiationException {
//        User user = new User();
//        Class clzz = User.class;
//        Class clzz = user.getClass();
        Class clzz = Class.forName("com.mine.model.User");
//        Method[] methods = User.class.getMethods();
//        Method[] methods = user.getClass().getMethods();
        Method[] methods = clzz.getMethods();
        Field[] fields = clzz.getDeclaredFields();
        Constructor c =clzz.getConstructor();
        Object obj = c.newInstance();
        for (Field field : fields) {
            System.out.println(field.getName());
            //修改该属性的值
            if (field.getName().equals("name")) {
                field.set(obj, "傻子瓜子");
            }
            System.out.println(obj);
        }

        for (Method method : methods) {
            System.out.println(method.getName());
            if (method.getName().equals("say1")) {
                method.invoke(obj);
            }
        }
    }

    /**
     * 测试通过流获取配置文件，同时通过构造器生成对象
     *
     * @throws IOException
     * @throws NoSuchMethodException
     * @throws InvocationTargetException
     * @throws IllegalAccessException
     */
    @Test
    public void test6() throws IOException, NoSuchMethodException, InvocationTargetException, IllegalAccessException, InstantiationException, ClassNotFoundException {
        File configFile = new File("D:\\测试文件夹\\ceshi.txt");
        Properties config = new Properties();
        config.load(new FileInputStream(configFile));
        String className = (String) config.get("class");
        String methodName = (String) config.get("method");
        Class clazz = Class.forName(className);
        Constructor c = clazz.getConstructor();
        //根据构造器，实例化出对象
        Object obj = c.newInstance();
        Method method = clazz.getMethod(methodName);
        method.invoke(obj);
    }

}
