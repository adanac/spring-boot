package com.feagle.learn.exception;

import com.feagle.learn.enums.ResultEnum;

/**
 * Created by feagle on 2017/5/23.
 */
public class GirlException extends RuntimeException {
    private Integer code;
    public GirlException(ResultEnum resultEnum) {
        super(resultEnum.getMessage());
        this.code = resultEnum.getCode();
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }
}
