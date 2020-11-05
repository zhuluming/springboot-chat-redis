package com.laywerspringboot.edition;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;


@SpringBootApplication
@MapperScan(basePackages = "com.laywerspringboot.edition.dao")
@ServletComponentScan
public class EditionApplication {

    public static void main(String[] args) {
        SpringApplication.run(EditionApplication.class, args);
    }

}
