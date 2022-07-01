package com.johnyehyo.imgencry.enums;

/**
 * @description:
 * @author: JohnYehyo
 * @create: 2022-06-30 22:52:47
 */
public enum ResponseEnum {

    SUCCESS(0, "操作成功"),
    FAIL(1, "操作失败，请稍后重试"),
    EXCEPTION(1111, "系统异常，请稍后重试");

    ResponseEnum(Integer code, String value) {
        this.code = code;
        this.value = value;
    }

    public Integer getCode() {
        return code;
    }

    public String getValue() {
        return value;
    }

    private Integer code;
    private String value;
}
