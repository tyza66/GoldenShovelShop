package com.tyza66.user;

import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Hello world!
 *
 */
@SpringBootApplication
@MapperScan("com.tyza66.user.mapper")
@EnableDubbo
public class GSS_UserApplication
{
    public static void main( String[] args )
    {
        SpringApplication.run(GSS_UserApplication.class,args);
    }
}
