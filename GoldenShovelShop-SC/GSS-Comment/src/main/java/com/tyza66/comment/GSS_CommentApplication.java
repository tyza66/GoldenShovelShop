package com.tyza66.comment;

import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Hello world!
 *
 */

@SpringBootApplication
@MapperScan("com.tyza66.comment.mapper")
@EnableDubbo
public class GSS_CommentApplication
{
    public static void main( String[] args )
    {
        SpringApplication.run(GSS_CommentApplication.class, args);
    }
}
