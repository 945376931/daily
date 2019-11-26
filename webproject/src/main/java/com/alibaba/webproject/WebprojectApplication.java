package com.alibaba.webproject;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
/** 这是一个主程序类**/
@SpringBootApplication(scanBasePackages ="com.alibaba.webproject")
@MapperScan(basePackages = "com.alibaba.webproject.mybatis.dao")
public class WebprojectApplication {
    public static void main(String[] args) {
        SpringApplication.run(WebprojectApplication.class, args);
    }
}
