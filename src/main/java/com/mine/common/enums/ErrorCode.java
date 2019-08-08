package com.mine.common.enums;

/**
 * Created by PC on 2016/6/1.
 */
public enum ErrorCode {

    SUCCESS(0, "正常"),

    SERVER_ERROR(-1, "服务器异常"),
    
    ACT_NOTSATRT(-2, "活动未开始"),
    
    ACT_STOP(-3, "活动已结束"),
    
    MISS_INFO(1, "缺少必要信息"),

    INVALID_PARAM(-4,"参数错误"),

    DATA_NOT_EXISTS(-5,"数据不存在");

    private int code;
    private String message;

    ErrorCode(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
