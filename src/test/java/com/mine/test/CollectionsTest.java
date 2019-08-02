package com.mine.test;

import com.mine.model.User;
import lombok.extern.slf4j.Slf4j;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
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
        Constructor c = clzz.getConstructor();
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

    /**
     * 获取set集合的第一个元素
     */
    @Test
    public void test7() {
        Set<String> hotWordSet = new TreeSet<>();
        hotWordSet.add("1");
        hotWordSet.add("2");
        hotWordSet.add("3");
        System.out.println(hotWordSet.iterator().next());
    }

    /**
     * 使用JSONArray做List操作
     */
    @Test
    public void test8() {
        JSONObject jsonObject = new JSONObject();
//        Set<Integer> set = new HashSet<>();
//        set.add(1);
//        set.add(2);
//        set.add(3);
//        JSONArray jsonObject1 = JSONArray.fromObject(set);
//        jsonObject1.element(4);
//        System.out.println(jsonObject1.toString());
//        JSONArray jsonArray = new JSONArray();
//        jsonArray.element(1);
//        String json = jsonArray.toString();
//        JSONArray jsonArray1 = JSONArray.fromObject(json);
//        jsonArray1.element(2);
//        System.out.println(jsonArray1.toString());
        String json = "[4,523,4,211,5]";
        JSONArray jsonArray = JSONArray.fromObject(json);
        List<Integer> idArray = JSONArray.toList(jsonArray, Integer.class);
        for (Integer i : idArray) {
            System.out.println(i);
        }
    }

    /**
     * set转list
     */
    @Test
    public void test9() {
        Set<Integer> set = new HashSet<>();
        set.add(1);
        set.add(2);
        set.add(3);
        List<Integer> list = new ArrayList<>(set);
        list.set(0, 9999);
        Integer[] array = list.toArray(new Integer[list.size()]);
    }

    @Test
    public void test10() {
    }

}
