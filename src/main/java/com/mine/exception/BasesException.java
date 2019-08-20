package com.mine.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author jiangqd
 * @Description 基础异常类
 * @Date 2019/1/15.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class BasesException extends RuntimeException{

    /**
     * http错误状态码
     */
    private int code;

    /**
     * http错误信息
     */
    private String message;

}
