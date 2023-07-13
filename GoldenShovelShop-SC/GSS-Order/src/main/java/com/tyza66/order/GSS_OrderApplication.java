package com.tyza66.order;

import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Hello world!
 *
 */

@SpringBootApplication
@MapperScan("com.tyza66.order.mapper")
@EnableDubbo
public class GSS_OrderApplication
{
    public static void main( String[] args )
    {
        SpringApplication.run(GSS_OrderApplication.class, args);
    }
}
