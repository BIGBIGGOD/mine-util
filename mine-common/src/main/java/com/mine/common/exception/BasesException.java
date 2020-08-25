package com.mine.common.exception;

import com.mine.common.enums.CommonEnum;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * @author jiangqd
 * @Description 基础异常类
 * @Date 2019/1/15.
 */
@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
public class BasesException extends RuntimeException {

    /**
     * http错误状态码
     */
    private int code;

    /**
     * http错误信息
     */
    private String message;

    public BasesException(int code, String message) {
        super(message);
        this.code = code;
        this.message = message;
    }

    public BasesException(int code, String message, Throwable cause) {
        super(cause);
        this.code = code;
        this.message = message;
    }

    public BasesException(CommonEnum commonEnum) {
        super(commonEnum.getMessage());
        this.code = commonEnum.getCode();
        this.message = commonEnum.getMessage();
    }
}
