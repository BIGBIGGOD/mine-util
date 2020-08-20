package com.mine.security.model;

import java.io.Serializable;
import java.util.Date;

import lombok.Data;

/**
 * @author Administrator
 */
@Data
public class UserRoleDo implements Serializable {
    private Long id;

    private String name;

    private String description;

    private String adminCount;

    private Date createTime;

    private Integer status;

    private Integer sort;

    private static final long serialVersionUID = 1L;
}