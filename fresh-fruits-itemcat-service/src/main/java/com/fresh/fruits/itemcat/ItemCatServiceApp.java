package com.fresh.fruits.itemcat;

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
public class ItemCatServiceApp {

    public static void main(String[] args) {
        SpringApplication.run(ItemCatServiceApp.class,args);
    }
}
