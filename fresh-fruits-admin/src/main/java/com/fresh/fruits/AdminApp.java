package com.fresh.fruits;

import com.spring4all.swagger.EnableSwagger2Doc;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @BelongsProject: fresh-fruits
 * @BelongsPackage: com.fresh.fruits
 * @CreateTime: 2021-06-15 10:36
 * @Description: TODO
 */
@SpringBootApplication
@EnableFeignClients //开启OpenFeign支持
@EnableSwagger2Doc //开启Swagger支持
public class AdminApp {

    public static void main(String[] args) {
        SpringApplication.run(AdminApp.class,args);
    }
}
