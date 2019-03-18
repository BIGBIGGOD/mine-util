package com.mine.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * Created by jiangqd on 2019/3/11.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@XmlRootElement
public class User {

    public String name;
    private String pwd;
    private String sex;
    private int age;

    public User(String name, String sex, int age) {
        this.name = name;
        this.sex = sex;
        this.age = age;
    }
    public void say1() {
        System.out.println("你好啊");
    }
    public void say3() {
        System.out.println("你好");
    }
    public void say2() {
        System.out.println("好");
    }
}
