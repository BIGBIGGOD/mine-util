package com.mine.util.securityutil.entity;

import lombok.Getter;
import lombok.Setter;

/**
 * 用户注册参数
 *
 * @author macro
 * @date 2018/4/26
 */
@Getter
@Setter
public class SecurityUserRegisterParam {
    /**
     * 用户名
     */
    private String username;
    /**
     * 密码
     */
    private String password;
    /**
     * 用户头像
     */
    private String icon;
    /**
     * 邮箱
     */
    private String email;
    /**
     * 用户昵称
     */
    private String nickName;
    /**
     * 备注
     */
    private String note;
}
