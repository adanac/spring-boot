package com.feagle.learn.service.impl;

import com.feagle.learn.domain.Girl;
import com.feagle.learn.service.RedisService;
import io.protostuff.LinkedBuffer;
import io.protostuff.ProtobufIOUtil;
import io.protostuff.runtime.RuntimeSchema;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

/**
 * Created by feagle on 2017/5/31.
 */
@Service
public class RedisServiceImpl implements RedisService {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    // 动态生成序列化所用的schema
    private static RuntimeSchema<Girl> schema = RuntimeSchema.createFrom(Girl.class);

    @Autowired
    private JedisPool jedisPool;

    @Override
    public void set(String key, String value) throws Exception {
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            jedis.set(key, value);
        } finally {
            jedis.close();
        }
    }

    @Override
    public String get(String key) throws Exception {
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            String result = jedis.get(key);
            return result;
        } finally {
            jedis.close();
        }
    }

    @Override
    public Girl getObj(long id) {
        try (Jedis jedis = jedisPool.getResource()) {
            /* 反序列化过程: get(byte[]) -> Girl */
            String key = "girl:" + id;
            byte[] bytes = jedis.get(key.getBytes());
            if (bytes != null) {
                // 生成空对象
                Girl girl = schema.newMessage();
                // 将字节数组中的内容, 按照schema, 传递到空对象中.
                ProtobufIOUtil.mergeFrom(bytes, girl, schema);
                return girl;
            }
        }
        return null;
    }

    @Override
    public String setObj(Girl girl) {
        try (Jedis jedis = jedisPool.getResource()) {
            /* 序列化过程: Girl -> byte[] */
            String key = "girl:" + girl.getId();
            byte[] bytes = ProtobufIOUtil.toByteArray(girl, schema,
                    LinkedBuffer.allocate(LinkedBuffer.DEFAULT_BUFFER_SIZE));

            // 设置缓存失效时间, 单位: 秒
            int timeout = 60 * 60;
            // 正确的话返回OK, 错误的话返回错误信息
            String result = jedis.setex(key.getBytes(), timeout, bytes);

            return result;
        }
    }
}
