package com.mine.model;

import java.io.Serializable;
import java.util.Date;

import lombok.Data;

@Data
public class UserLoginLogDo implements Serializable {
    private Long id;

    private Long userId;

    private String ip;

    private String address;

    private String userAgent;

    private Date createTime;

    private Date updateTime;

    private static final long serialVersionUID = 1L;
}