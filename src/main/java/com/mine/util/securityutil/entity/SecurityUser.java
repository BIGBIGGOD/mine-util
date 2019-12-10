package com.mine.util.securityutil.entity;

import java.util.Date;

import lombok.Data;

/**
 * @author jiangqingdong
 * @version v1.0
 * @Description
 * @DATE 2019/11/1 15:35
 * @Copyright Copyright © 2019 深圳花儿绽放网络科技股份有限公司. All rights reserved.
 */
@Data
public class SecurityUser {

    private String username;

    private String password;

    private Integer status;

    private Date createTime;
}
