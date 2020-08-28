package com.mine.mybatis.model;

import java.io.Serializable;
import java.util.Date;

import lombok.Data;

/**
 * @author Administrator
 */
@Data
public class UserDo implements Serializable {

    private Integer id;

    private String name;

    private String pwd;

    private String sex;

    private Integer age;

    private Date time;

    private static final long serialVersionUID = 1L;
}