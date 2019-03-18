package com.mine.util.httpClientUtil.result;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @Deprecated 发送http https 请求的返回结果包装类
 * Created by jiangqd on 2019/1/15.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class HttpResponseResult implements Serializable{

    /**
     * http响应状态码
     */
    private int errorCode;

    /**
     * http响应消息
     */
    private String errorMessage;

    /**
     * http响应内容
     */
    private String content;

    public HttpResponseResult(int errorCode, String errorMessage) {
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
    }
}
