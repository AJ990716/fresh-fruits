package com.fresh.fruits;

import com.spring4all.swagger.EnableSwagger2Doc;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @project: fresh-fruits;
 * @package: com.fresh.fruits;
 * @author: Administrator;
 * @date: 2021/7/8 23:17;
 * @Description:
 */
@SpringBootApplication
@EnableFeignClients
@EnableSwagger2Doc
public class BuyerWebApp {
    public static void main(String[] args) {
        SpringApplication.run(BuyerWebApp.class,args);
    }
}
