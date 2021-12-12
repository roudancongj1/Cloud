package com.gao;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
//@EnableTransactionManagement
public class GaoApplication {

    public static void main(String[] args) {
        SpringApplication.run(GaoApplication.class, args);
    }

}
