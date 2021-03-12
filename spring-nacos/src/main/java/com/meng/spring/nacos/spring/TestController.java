package com.meng.spring.nacos.spring;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author ZuoHao
 * @date 2021/3/12
 */
@RestController()
@RequestMapping("/test")
public class TestController {
    @Resource
    private NacosConfig config;

    @RequestMapping("/get")
    private String get(){
        return config.getName();
    }
}
