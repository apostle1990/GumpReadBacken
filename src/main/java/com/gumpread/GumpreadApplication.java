package com.gumpread;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.gumpread.mapper")
public class GumpreadApplication {

    public static void main(String[] args) {
        SpringApplication.run(GumpreadApplication.class, args);
    }

}
