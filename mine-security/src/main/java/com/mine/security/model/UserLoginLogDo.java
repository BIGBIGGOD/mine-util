package com.mine.security.model;

import java.io.Serializable;
import java.util.Date;

import lombok.Data;

@Data
public class UserLoginLogDo implements Serializable {
    private Long id;

    /**
     * 关联用户主键id
     */
    private Long userId;

    private String ip;

    private String address;
    /**
     * 浏览器登录类型
     */
    private String userAgent;

    private Date createTime;

    private Date updateTime;

    private static final long serialVersionUID = 1L;
}