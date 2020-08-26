package com.mine.security.entity;

import java.io.Serializable;
import java.util.Date;

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
public class SecurityUser implements Serializable {

    private Integer id;

    private String name;

    private String pwd;

    private String sex;

    private Integer age;

    private Date time;

    public SecurityUser(String name, String pwd) {
        this.name = name;
        this.pwd = pwd;
    }

}
