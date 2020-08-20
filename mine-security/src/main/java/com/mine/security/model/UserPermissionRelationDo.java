package com.mine.security.model;

import java.io.Serializable;

import lombok.Data;

@Data
public class UserPermissionRelationDo implements Serializable {
    private Long id;

    private Long userId;

    private Long permissionId;

    private Integer type;

    private static final long serialVersionUID = 1L;
}