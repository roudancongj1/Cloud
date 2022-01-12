package com.sch.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @Auth: Gao
 * @Date: 2022/1/12 22:53
 */
@Configuration
public class config implements WebMvcConfigurer {

 //   @Override
 //   public void addInterceptors(InterceptorRegistry registry) {
 //       registry.addInterceptor(new crossInterceptor()).addPathPatterns("/*");
 //   }
}
