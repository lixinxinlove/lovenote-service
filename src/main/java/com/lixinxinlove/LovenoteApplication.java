package com.lixinxinlove;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
@MapperScan(basePackages = "com.lixinxinlove.entity.mapper")
public class LovenoteApplication {

    public static void main(String[] args) {
        SpringApplication.run(LovenoteApplication.class, args);
    }
}
