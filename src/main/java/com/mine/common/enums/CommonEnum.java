package com.mine.common.enums;

/**
 * @eprecated 常用枚举
 * Created by jiangqd on 2019/1/15.
 */
public enum CommonEnum {

    // 成功
    SUCCESS(0, "成功"),
    FAILURE(1, "失败"),
    // 错误
    PARAMS_ERROR(-1, "参数错误"),
    SERVER_BUSY(-2, "系统繁忙"),
    SYSTEM_ERROR(-3, "系统错误"),
    OPERATE_DB_FAIL(-4, "数据库操作失败"),
    UNKNOWN(-5, "未知错误"),
    INTERFAC_EERROR(-6,"调用接口错误");

    private int code;
    private String message;

    CommonEnum(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public static CommonEnum getByCode(int code) {
        for (CommonEnum commonEnum : values()) {
            if (commonEnum.getCode() == code) {
                return commonEnum;
            }
        }
        return null;
    }
}
