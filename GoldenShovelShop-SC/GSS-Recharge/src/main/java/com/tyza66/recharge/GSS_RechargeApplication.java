package com.tyza66.recharge;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Hello world!
 *
 */

@SpringBootApplication
@MapperScan("com.tyza66.recharge.mapper")
public class GSS_RechargeApplication
{
    public static void main( String[] args )
    {
        SpringApplication.run(GSS_RechargeApplication.class, args);
    }
}
