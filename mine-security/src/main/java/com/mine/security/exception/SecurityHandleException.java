package com.mine.security.exception;

import com.mine.common.enums.CommonEnum;
import com.mine.common.exception.BasesException;
import lombok.extern.slf4j.Slf4j;

/**
 * @author jiangqingdong
 * @date 2017/2/20
 */

@Slf4j
public class SecurityHandleException extends BasesException {

    public SecurityHandleException(int code, String message) {
        super(code, message);
    }

    public SecurityHandleException(int code, String message, Throwable cause) {
        super(code, message, cause);
    }

    public SecurityHandleException(CommonEnum commonEnum) {
        super(commonEnum);
    }
}
