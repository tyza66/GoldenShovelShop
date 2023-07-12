package com.tyza66.goods;

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
public class GSS_GoodsApplication
{
    public static void main( String[] args )
    {
        SpringApplication.run(GSS_GoodsApplication.class, args);
    }
}
