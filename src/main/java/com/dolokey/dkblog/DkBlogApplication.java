package com.dolokey.dkblog;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author dolokey
 * @date 2025/9/12
 */
@SpringBootApplication
@MapperScan("com.dolokey.dkblog.mapper")
public class DkBlogApplication {

    public static void main(String[] args) {
        SpringApplication.run(DkBlogApplication.class, args);
    }

}
