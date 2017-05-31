package com.feagle.learn.util;

import com.feagle.learn.domain.Result;

/**
 * Created by feagle on 2017/5/23.
 */
public class ResultUtil {


    public static Result success(Object object) {
        Result result = new Result();
        result.setCode(0);
        result.setMsg("成功");
        result.setData(object);
        return result;
    }

    public static Result success() {
        return success(null);
    }

    public static Result error(Integer code, String errorMsg) {
        Result result = new Result();
        result.setCode(code);
        result.setMsg(errorMsg);
        return result;
    }


}
