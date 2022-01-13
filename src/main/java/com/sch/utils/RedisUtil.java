package com.sch.utils;

import org.springframework.data.redis.core.RedisTemplate;

/**
 * @Auth: Gao
 * @Date: 2022/1/13 15:30
 */


public class RedisUtil {

    private RedisTemplate redisTemplate;

    public Boolean set(String str,Object o){
        try {
            redisTemplate.opsForValue().set(str,o);
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    public Object get(String str){
        try {
            return redisTemplate.opsForValue().get(str);
        } catch (Exception e) {
            return null;
        }
    }
}
