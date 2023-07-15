package com.tyza66.gift;

import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * Hello world!
 *
 */

@SpringBootApplication(scanBasePackages = {"com.tyza66.gift"})
@MapperScan("com.tyza66.gift.mapper")
@EnableDubbo
@EnableTransactionManagement
public class  GSS_GiftApplication
{
    public static void main( String[] args )
    {
        SpringApplication.run(GSS_GiftApplication.class, args);
    }
}
