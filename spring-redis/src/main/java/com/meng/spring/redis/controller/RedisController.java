package com.meng.spring.redis.controller;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author ZuoHao
 * @date 2021/3/20
 */
@RestController
@RequestMapping("/redis")
public class RedisController {

    @Resource
    private RedisTemplate<String, Object> redisTemplate;

    @RequestMapping("/set")
    public String set() {
        redisTemplate.opsForValue().set("1", 2);
        return "success";
    }

    @RequestMapping("/get")
    public String get() {
        Object o = redisTemplate.opsForValue().get("1");
        System.out.println(o);
        return "success";
    }

    @RequestMapping("/del")
    public String del() {
        Boolean delete = redisTemplate.delete("1");
        System.out.println(delete);
        return "success";
    }
}
