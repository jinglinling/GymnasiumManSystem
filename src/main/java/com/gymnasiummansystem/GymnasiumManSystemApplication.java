package com.gymnasiummansystem;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@MapperScan(basePackages = "com.gymnasiummansystem.mapper")
@EnableTransactionManagement
@SpringBootApplication
public class GymnasiumManSystemApplication {

    public static void main(String[] args) {
        SpringApplication.run(GymnasiumManSystemApplication.class, args);
    }

}
