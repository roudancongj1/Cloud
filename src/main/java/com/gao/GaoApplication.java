package com.gao;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
//@EnableTransactionManagement 开启事务  @Transaction
@EnableCaching
//@EnableAsync  开启异步  @Async
//@EnableScheduling  开启定时任务  @Scheduled
public class GaoApplication {

    public static void main(String[] args) {
        SpringApplication.run(GaoApplication.class, args);
    }

}
