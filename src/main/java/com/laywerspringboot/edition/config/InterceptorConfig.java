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
        String[] patterns = new String[]{"/error", "/userInfo/register/**","/userInfo/userLogin","/userInfo/phoneLogin",
                "/swagger-resources/**","/webjars/**","/v2/**","/swagger-ui.html/**","/uploadImgToOSS",
                "/jquery-1.8.0.min.js:3"};

        registry.addInterceptor(new JWTInterceptor())
                //.addPathPatterns("/**")  //其他接口token验证
                //.excludePathPatterns(patterns);//放行的请求
        .excludePathPatterns("/**");


    }

}
