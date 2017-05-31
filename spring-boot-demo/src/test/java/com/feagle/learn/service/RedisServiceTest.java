package com.feagle.learn.service;

import com.feagle.learn.domain.Girl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.*;

/**
 * redis相关操作的单元测试
 * Created by feagle on 2017/5/31.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class RedisServiceTest {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private RedisService redisService;

    @Test
    public void set() throws Exception {
        String key = "allen";
        String value = "1521";
        redisService.set(key, value);
    }

    @Test
    public void get() throws Exception {
        String key = "allen";
        String result = redisService.get(key);
        logger.info("result={}",result);
    }

    /**
     * 测试反序列化
     * @throws Exception
     */
    @Test
    public void getObj() throws Exception {
        long id = 123L;
        Girl girl = redisService.getObj(id);
        logger.info("girl={}",girl);
    }

    /**
     * 测试对象的序列化
     * @throws Exception
     */
    @Test
    public void setObj() throws Exception {
        Girl girl = new Girl();
        girl.setId(123);
        girl.setCupSize("BB");
        girl.setAge(25);
        girl.setMoney(6000.32);
        String result = redisService.setObj(girl);
        logger.info("result={}",result);
    }

}