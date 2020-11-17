package com.mine.utils.httpClientUtil.result;

import lombok.Data;
import lombok.ToString;

/**
 * Created by jiangqd on 2019/1/16.
 */
@Data
@ToString
public class ConnectResult extends CommonResult{

    private String corpAccessToken;
    private String corpId;
    private long expiresIn;
}
