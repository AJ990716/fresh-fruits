package com.fresh.fruits;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @BelongsProject: fresh-fruits
 * @BelongsPackage: com.fresh.fruits
 * @CreateTime: 2021-06-15 10:02
 * @Description: TODO
 */
@SpringBootApplication
@MapperScan("com.fresh.fruits.mapper")
public class ContentServiceApp {

    public static void main(String[] args) {
        SpringApplication.run(ContentServiceApp.class,args);
    }
}
