package com.sch.utils;

import java.util.HashMap;
import java.util.Map;

/**
 * @Auth: Gao
 * @Date: 2022/1/11 0:32
 */
public class ResultUtil extends HashMap<String,Object> {

    //默认成功
    ResultUtil(){
        put("code","0");
        put("msg","success");
    }

    ResultUtil(String code, Object msg){
        put("code",code);
        put("msg",msg);
    }

    public static ResultUtil ok(){
        return new ResultUtil();
    }

    public static ResultUtil ok(String msg){
        return new ResultUtil("0",msg);
    }

    public static ResultUtil ok(Integer code, String msg){
        return new ResultUtil(code.toString(),msg);
    }

    public static ResultUtil error(){
        return new ResultUtil("500","操作失败");
    }

    public static ResultUtil error(String msg){
        return new ResultUtil("500",msg);
    }

    public static ResultUtil error(Integer code, String msg){
        return new ResultUtil(code.toString(),msg);
    }

    @Override
    public ResultUtil put(String key, Object value) {
        super.put(key, value);
        return this;
    }

    public ResultUtil put(Object data){
        super.put("data",data);
        return this;
    }
    //return的this指向当前方法返回对象
    public ResultUtil put(Map<String,Object> map){
        putAll(map);
        return this;
    }

}
