package com.sch.utils;

import java.util.HashMap;
import java.util.Map;

/**
 * @Auth: Gao
 * @Date: 2022/1/11 0:32
 */
public class resultUtil extends HashMap<String,Object> {

    //默认成功
    resultUtil(){
        put("code","0");
        put("msg","success");
    }

    resultUtil(Integer code,Object msg){
        put("code",code);
        put("msg",msg);
    }

    public static resultUtil ok(){
        return new resultUtil();
    }

    public static resultUtil ok(String msg){
        return new resultUtil(0,msg);
    }

    public static resultUtil ok(Integer code,String msg){
        return new resultUtil(code,msg);
    }

    public static resultUtil error(){
        return new resultUtil(500,"操作失败");
    }

    public static resultUtil error(String msg){
        return new resultUtil(500,msg);
    }

    public static resultUtil error(Integer code,String msg){
        return new resultUtil(code,msg);
    }

    @Override
    public resultUtil put(String key, Object value) {
        super.put(key, value);
        return this;
    }

    public resultUtil put(Object data){
        super.put("data",data);
        return this;
    }
    //return的this指向当前方法返回对象
    public resultUtil put(Map<String,Object> map){
        putAll(map);
        return this;
    }

}
