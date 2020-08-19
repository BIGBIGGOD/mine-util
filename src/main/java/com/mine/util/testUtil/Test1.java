package com.mine.util.testUtil;

import java.util.ArrayDeque;
import java.util.Comparator;
import java.util.Queue;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentSkipListMap;

import com.mine.entity.User;
import lombok.Data;

/**
 * Created by jiangqd on 2019/3/16.
 */
@Data
public class Test1 {

    public static void main(String[] args) throws ClassNotFoundException {
        ConcurrentHashMap<String, String> map = new ConcurrentHashMap<>();
        map.put("ss", "xx");
        map.get("ss");

        ConcurrentSkipListMap<String, String> xxx = new ConcurrentSkipListMap<>();
        xxx.put("xx", "xxsf");
        xxx.get("xx");
        System.out.println(xxx);

        ConcurrentSkipListMap<User, String> aaa = new ConcurrentSkipListMap<>(new Comparator<User>() {
            @Override
            public int compare(User o1, User o2) {
                return o1.getAge() - o2.getAge();
            }
        });

    }

}
