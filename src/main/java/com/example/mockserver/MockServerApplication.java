package com.example.mockserver;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
@MapperScan(basePackages = "com.example.mockserver.mapper")
public class MockServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(MockServerApplication.class, args);
    }

}
