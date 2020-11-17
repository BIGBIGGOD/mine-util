package com.mine.utils.httpClientUtil.result;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @Deprecated 基础返回结果类
 * Created by jiangqd on 2019/1/16.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CommonResult implements Serializable{

    private Integer errorCode;

    private String errorMessage;

}
