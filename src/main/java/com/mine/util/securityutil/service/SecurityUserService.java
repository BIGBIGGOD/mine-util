package com.mine.util.securityutil.service;

import com.mine.util.securityutil.entity.SecurityUser;
import com.mine.util.securityutil.entity.SecurityUserParam;

/**
 * 后台管理员Service
 *
 * @author macro
 * @date 2018/4/26
 */
public interface SecurityUserService {
    /**
     * 根据用户名获取后台管理员
     */
    SecurityUser getUserByUsername(String username);

    /**
     * 注册功能
     */
    SecurityUser register(SecurityUserParam SecurityUserParam);

    /**
     * 登录功能
     *
     * @param username 用户名
     * @param password 密码
     * @return 生成的JWT的token
     */
    String login(String username, String password);
}
