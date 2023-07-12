package com.tyza66.goods;

import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.swing.*;

/**
 * Hello world!
 *
 */

@SpringBootApplication
@MapperScan("com.tyza66.goods.mapper")
@EnableDubbo
public class GSS_GoodsApplication
{
    public static void main( String[] args )
    {
        SpringApplication.run(GSS_GoodsApplication.class, args);
    }
}
