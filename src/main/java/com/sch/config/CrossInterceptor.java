package com.sch.config;

import org.springframework.http.HttpHeaders;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @Auth: Gao
 * @Date: 2022/1/12 22:47
 */
public class CrossInterceptor implements HandlerInterceptor {
 //  HandlerInterceptor拦截器
 // preHander：被@RequestMapping注解的方法执行前调用
 // postHander：被@RequestMapping注解的方法执行后未返回ModelView之前调用
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if (request.getHeader(HttpHeaders.ORIGIN) != null) {
            //String origin = request.getHeader("Origin");
            response.addHeader("Access-Control-Allow-Origin", "*");//*
            response.addHeader("Access-Control-Allow-Credentials", "true");//
            response.addHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, DELETE, PUT,PATCH, HEAD");//
            response.addHeader("Access-Control-Allow-Headers", "Content-Type");
            response.addHeader("Access-Control-Max-Age", "3600");
        }
        return true;
    }
}
