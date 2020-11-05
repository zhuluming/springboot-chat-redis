package com.laywerspringboot.edition.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @Author:小七
 * @createTime:2020-10-24-22-59
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig
{

    @Bean
    public Docket getDocket(){

        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .useDefaultResponseMessages(false)
                .select()
                .paths(PathSelectors.any())
                .apis(RequestHandlerSelectors.any())
                //.paths(Predicates.or(PathSelectors.regex("/api2/.*")))//  过滤  该路径下的请求生成RESTful api
                .build();
    }

    private ApiInfo apiInfo(){
        return new ApiInfoBuilder()
                .title("BookStore Platform API")//大标题
                .description("BookStore Platform's REST API, all the applications could access the Object model data via JSON.")//详细描述
                .version("2.9")//版本
                .contact(new Contact("java", "http://itqf.com", "775773552@qq.com"))//作者
                .license("The Apache License, Version 2.0")//许可证信息
                .licenseUrl("http://www.apache.org/licenses/LICENSE-2.0.html")//许可证地址
                .build();

    }

}