package com.feagle.learn.handler;

import com.feagle.learn.domain.Result;
import com.feagle.learn.exception.GirlException;
import com.feagle.learn.util.ResultUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by feagle on 2017/5/23.
 */
@ControllerAdvice
public class ExceptionHandle {
    private static final Logger logger = LoggerFactory.getLogger(ExceptionHandle.class);

    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public Result exception(Exception e) {
        Result result = new Result();
        if (e instanceof GirlException) {
            GirlException girlException = (GirlException) e;
            return ResultUtil.error(girlException.getCode(), e.getMessage());
        } else {
            logger.info("[系统异常]{}",e);
            return ResultUtil.error(-1, "未知异常");
        }
    }
}
