package com.feagle.learn.enums;

/**
 * Created by feagle on 2017/5/24.
 */
public enum  ResultEnum {
    SUCCESS(0,"成功"),
    UNKNOW_ERROR(-1,"未知错误"),
    PRIMARY_SCHOOL(100,"可能还在上小学吧"),
    MIDDLE_SCHOOL(101,"可能还在上初中吧"),
    ;

    ResultEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    private Integer code;
    private String message;

    public Integer getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
