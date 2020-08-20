package com.mine.security.model;

import java.io.Serializable;
import java.util.Date;

import lombok.Data;

/**
 * @author Administrator
 */
@Data
public class UserPermissionDo implements Serializable {
    private Long id;

    private Long pid;

    private String name;

    private String value;

    private String icon;

    private Integer type;

    private String uri;

    private Integer status;

    private Date createTime;

    private Integer sort;

    private static final long serialVersionUID = 1L;
}