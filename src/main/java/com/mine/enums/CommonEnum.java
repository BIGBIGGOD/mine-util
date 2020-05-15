package com.mine.enums;

/**
 * @author jiangqd
 * @eprecated 常用枚举
 * Created by jiangqd on 2019/1/15.
 */
public enum CommonEnum {

    // 成功
    SUCCESS(0, "成功"),
    // 错误
    FAILURE(-1, "失败"),
    PARAMS_ERROR(-2, "参数错误"),
    SERVER_BUSY(-3, "系统繁忙"),
    SYSTEM_ERROR(-4, "系统错误"),
    OPERATE_DB_FAIL(-5, "数据库操作失败"),
    UNKNOWN(-6, "未知错误"),
    INTERFACE_EERROR(-7, "调用接口错误"),
    DATA_NOT_EXISTS(-8, "数据不存在"),
    OLD_PARAMS_ERR(-9, "旧密码错误")
    ;

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
