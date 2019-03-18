package com.mine.test;

import com.google.gson.Gson;

/**
 * Created by jiangqd on 2019/2/21.
 */
public class EmpTest {

    public static void main(String[] args) {
//        Emp emp = new Emp("悟空","123");
        Person person = new Person("八戒", "456", "朱");
//        Emp emp = Emp.builder()
//            .name("悟空")
//            .pwd("123")
//            .build();
//                Emp emp = new Emp();
//        emp.setName("悟空");
        Gson gson = new Gson();
//        Person person = gson.fromJson(gson.toJson(emp),Person.class);
        Emp emp = gson.fromJson(gson.toJson(person), Emp.class);
        System.out.println(emp);
    }

}
