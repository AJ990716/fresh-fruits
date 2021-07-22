package com.fresh.fruits;

import com.spring4all.swagger.EnableSwagger2Doc;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
@EnableSwagger2Doc
public class SearchWebApp {
    public static void main(String[] args) {
        SpringApplication.run(SearchWebApp.class,args);
    }
}
