package com.tyza66.recharge;

import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * Author: tyza66
 * Date: 2023/7/13 10:21
 * Github: https://github.com/tyza66
 **/

@SpringBootApplication
@MapperScan("com.tyza66.recharge.mapper")
@EnableDubbo
public class GSS_RechargeApplication {
    public static void main(String[] args) {
        SpringApplication.run(GSS_RechargeApplication.class,args);
    }
}
