package com.sch.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.sql.Time;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @Auth: Gao
 * @Date: 2022/1/25 20:11
 */
@Aspect
@Component
public class ThreadLocalAspect {
    @Autowired
    private RedisUtil redisUtil;

    @Around("execution(* com.sch.controller.work..*.*(..))")
    public Object doAround(ProceedingJoinPoint point) throws Throwable{

        System.out.println("---------------------------切面被执行----------------------------------");
        ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();

        if(null == requestAttributes)
            throw new RuntimeException("未捕获到响应请求");

        String token = requestAttributes.getRequest().getHeader("token");

        //获取入参
        Object[] args = point.getArgs();

        //获取方法返回值
        Object proceed = point.proceed();

        if(redisUtil.hasKey(token)){
            System.out.println("---------------------------判断token----------------------------------");
            ThreadLocalUtil.getThreadlocal().set(JSON.parseObject(JSON.toJSONString(redisUtil.get("token:"+token))));
        }else {
            return ResultUtil.error("用户未登录");
        }
        return proceed;
    }
}
