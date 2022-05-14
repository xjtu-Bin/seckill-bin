package com.example.seckillbin;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.example.seckillbin.mapper")
public class SeckillBinApplication {

    public static void main(String[] args) {
        SpringApplication.run(SeckillBinApplication.class, args);
    }

}
