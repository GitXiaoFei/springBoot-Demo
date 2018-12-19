package com.springboot;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.springboot.dao")
public class SpringBootDemoApplication {
    
    public static void main(String[] args) {
        SpringApplication.run(SpringBootDemoApplication.class, args);
    }
    
}
