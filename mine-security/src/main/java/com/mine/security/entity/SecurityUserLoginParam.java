package com.mine.security.entity;

import lombok.Data;

/**
 * 用户登录参数
 *
 * @author macro
 * @date 2018/4/26
 */
@Data
public class SecurityUserLoginParam {

    private String username;

    private String password;
}
