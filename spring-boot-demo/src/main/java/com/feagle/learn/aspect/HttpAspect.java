package com.feagle.learn.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;


/**
 * Created by feagle on 2017/5/23.
 */
@Aspect
@Component
public class HttpAspect {

    private Logger logger = LoggerFactory.getLogger(HttpAspect.class);

    @Pointcut("execution(public * com.feagle.learn.controller.GirlController.*(..))")
    public void log() {

    }

    @Before("log()")
    public void doBefore(JoinPoint joinPoint) {
        ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = requestAttributes.getRequest();
        //请求url
        logger.info("url={}", request.getRequestURL());
        //请求method
        logger.info("method={}", request.getMethod());
        //ip
        logger.info("ip={}", request.getRemoteAddr());
        //请求的类方法
        logger.info("class_method={}", joinPoint.getSignature().getDeclaringTypeName() + "." + joinPoint.getSignature().getName());
        //请求参数
        logger.info("args={}", joinPoint.getArgs());
    }

    @After("log()")
    public void doAfter() {
        logger.info("doAfter...");
    }

    @AfterReturning(returning = "object",pointcut = "log()")
    public void doAfterReturning(Object object) throws Exception {
        if (object!=null) {
            logger.info("response={}",object.toString());
        }else{
            throw new Exception("return object is null");
        }
    }

}
