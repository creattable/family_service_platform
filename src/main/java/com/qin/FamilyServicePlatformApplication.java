package com.qin;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
//别忘了写@MapperScan，不然就只能一个个在每个mapper接口上手写@mapper了，会累死的
@MapperScan
public class FamilyServicePlatformApplication {
    
    public static void main(String[] args) {
        SpringApplication.run(FamilyServicePlatformApplication.class, args);
    }
    
}
