package com.mine.util.securityutil.entity;

import lombok.Data;

/**
 * 修改用户名密码参数
 *
 * @author macro
 * @date 2019/10/9
 */
@Data
public class UpdateUserPasswordParam {
    /**
     * 用户名
     */
    private String username;
    /**
     * 旧密码
     */
    private String oldPassword;
    /**
     * 新密码
     */
    private String newPassword;
}
