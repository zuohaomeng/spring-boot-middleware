package com.meng.spring.nacos;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@EnableConfigurationProperties
@SpringBootApplication
public class SpringNacosApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringNacosApplication.class, args);
    }

}
