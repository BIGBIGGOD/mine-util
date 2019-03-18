package com.mine.util.httpClientUtil.arg;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created by jiangqd on 2019/1/16.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class BaseArg implements Arg {

    /**
     * 第三方应用获得企业授权的凭证
     */
    protected String corpAccessToken;

    /**
     * 开放平台派发的公司账号
     */
    protected String corpId;

}
