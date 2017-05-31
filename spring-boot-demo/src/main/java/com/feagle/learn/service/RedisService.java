package com.feagle.learn.service;

import com.feagle.learn.domain.Girl;

/**
 * Created by feagle on 2017/5/31.
 */
public interface RedisService {
    /**
     * 将对象写入redis缓存中
     * @param key
     * @param value
     * @throws Exception
     */
    void set(String key, String value) throws Exception;

    /**
     * 从redis缓存中获取对象
     * @param key
     * @return
     * @throws Exception
     */
    String get(String key) throws Exception;

    /**
     * 使用protostuff序列化对象到redis缓存中
     * @param girl
     */
    String setObj(Girl girl);

    /**
     * 使用protostuff反序列化从redis缓存中获取对象
     * @param id
     * @return
     */
    Girl getObj(long id);
}
