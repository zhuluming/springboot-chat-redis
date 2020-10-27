package com.laywerspringboot.edition.config;

import com.laywerspringboot.edition.interceptors.JWTInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @Author:小七
 * @createTime:2020-10-26-22-01
 */
@Configuration
public class InterceptorConfig implements WebMvcConfigurer {
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new JWTInterceptor())
                .excludePathPatterns("/userInfo/**","/userInfo/userLogin","/userInfo/phoneLogin")//放行的请求
                .addPathPatterns("/**") ;        //其他接口token验证

    }
}
