package com.mine.common.entity;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlRootElement;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * Created by jiangqd on 2019/3/11.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
//加了XmlRootElement注释的实体类在mvc返回的时候会报存在相同属性存在多个的错误
@XmlRootElement
@Accessors(chain = true)
public class User implements Serializable {

    public String name;
    private String pwd;
    private String sex;
    private int age;
    private long time;

    public User(String name, String pwd) {
        this.name = name;
        this.pwd = pwd;
    }

    public User(String name, String sex, int age) {
        this.name = name;
        this.sex = sex;
        this.age = age;
    }

    public User(String name) {
        this.name = name;
    }

    public User(int age) {
        this.age = age;
    }

    public void say4(Integer str) {
        System.out.println(str);
        System.out.println("nishiakgjeifjaewfjeahj");
    }

    public static User buildUserBean(String type) {
        if ("悟空".equals(type)) {
            return new User("悟空");
        }
        if ("八戒".equals(type)) {
            return new User("八戒");
        }
        throw new IllegalArgumentException("type must be \"悟空\" or \"八戒\"");
    }
}
