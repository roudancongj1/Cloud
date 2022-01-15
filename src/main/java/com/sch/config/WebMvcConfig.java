package com.sch.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @Auth: Gao
 * @Date: 2022/1/12 22:53
 */
@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

   @Override
   public void addInterceptors(InterceptorRegistry registry) {
       registry.addInterceptor(new CrossInterceptor()).addPathPatterns("/**");
   }
}
