package com.sch.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.sch.pojo.User;
import org.springframework.stereotype.Component;

/**
 * @Auth: Gao
 * @Date: 2022/1/25 22:46
 */

@Component
public class ThreadLocalUtil {

    private final static ThreadLocal<JSONObject> threadlocal= new ThreadLocal<>();

    public static ThreadLocal<JSONObject> getThreadlocal(){
        return threadlocal;
    }


    public static JSONObject getThreadLocalUser(){
        if(threadlocal.get() == null){
            return new JSONObject();
        }
        JSONObject jsonObject=JSON.parseObject(JSON.toJSONString(threadlocal.get()));
        return jsonObject;
    }

}
