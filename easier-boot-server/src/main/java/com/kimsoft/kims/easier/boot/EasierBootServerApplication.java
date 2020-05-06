package com.kimsoft.kims.easier.boot;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.kimsoft.kims.easier.boot.user.mapper")
public class EasierBootServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(EasierBootServerApplication.class, args);
    }

}
