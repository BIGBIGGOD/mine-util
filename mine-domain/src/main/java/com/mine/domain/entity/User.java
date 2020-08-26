package com.mine.domain.entity;

import java.io.Serializable;
import java.util.Date;

import javax.xml.bind.annotation.XmlRootElement;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 *
 * @author jiangqd
 * @date 2019/3/11
 */
@Data
@Accessors(chain = true)
@NoArgsConstructor
public class User implements Serializable {

    private Integer id;

    private String name;

    private String pwd;

    private String sex;

    private Integer age;

    private Date time;

    public User(String name, String pwd) {
        this.name = name;
        this.pwd = pwd;
    }

}
