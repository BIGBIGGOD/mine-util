package com.mine;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.mine.mapper.CeShiMapper;
import lombok.extern.slf4j.Slf4j;

/**
 * Created by jiangqd on 2019/1/12.
 */
@Slf4j
public class UserTest {

    @Autowired
    private CeShiMapper ceShiMapper;

    @Test
    public void test1() {
        log.info("你好啊");
        List<String> list = ceShiMapper.getUsername();
        System.out.println(list.toString());
        ceShiMapper.addUser("123456789-123456789-123456789");
    }

    @Test
    public void test2() {
        int i = 128;
        Integer i2 = 128;
        Integer i3 = new Integer(128);
        //Integer会自动拆箱为int，所以为true
        System.out.println(i == i2);
        System.out.println(i == i3);
        System.out.println("**************");
        Integer i5 = 127;//java在编译的时候,被翻译成-> Integer i5 = Integer.valueOf(127);
        Integer i6 = 127;
        System.out.println(i5 == i6);//true
        Integer a = 127;
        Integer b = 127;
        System.out.println(a == b);
        System.out.println(i2 == i3);
    }

    @Test
    public void test3() throws IOException {
        Map<String, Object> map = new HashMap<>();
        Map<String, Object> map1 = new HashMap<>();
        map.put("dd", "ji");
        map.put("dd54", 545);
        map.put("map1", new Gson().toJson(map1));
        String str = map.toString();
        String json = new Gson().toJson(map);
        ObjectMapper mapper = new ObjectMapper();
        HashMap<String, Object> jsonMap = mapper.readValue(json, HashMap.class);
    }

    @Test
    public void test4() {
//        while (true) {
//            Scanner in = new Scanner(System.in);
//            int n = in.nextInt();
            int n = -300;
            if ((n % 300) == 0) {
                System.out.println("你好啊");
            } else {
                System.out.println("唉");
            }
//        }
    }

    public static void main(String[] args) {
        while (true) {
            Scanner in = new Scanner(System.in);
            int n = in.nextInt();
            if ((n % 300) == 0) {
                System.out.println("你好啊");
            } else {
                System.out.println("唉");
            }
        }
    }

}
