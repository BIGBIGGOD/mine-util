package com.mine.model;

import javax.xml.bind.annotation.XmlRootElement;

import com.mine.util.httpClientUtil.result.CommonResult;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * Created by jiangqd on 2019/3/11.
 */
@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
@XmlRootElement
public class User extends CommonResult {

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

    public static User buildUserBean(String type){
        if ("悟空".equals(type)) {
            return new User("悟空");
        }
        if ("八戒".equals(type)) {
            return new User("八戒");
        }
        throw  new IllegalArgumentException("type must be \"悟空\" or \"八戒\"");
    }
}
