package com.meng.spring.zookepper.controller;

import org.apache.curator.framework.CuratorFramework;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author ZuoHao
 * @date 2021/2/7
 */
@RestController
public class HelloController {
    @Resource
    private CuratorFramework curatorFramework;

    public String lock(){
        return "lock";
    }
}
