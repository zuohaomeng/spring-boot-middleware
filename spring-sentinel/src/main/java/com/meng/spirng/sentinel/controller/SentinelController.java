package com.meng.spirng.sentinel.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.meng.spirng.sentinel.service.SentinelService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author ZuoHao
 * @date 2021/3/18
 */
@RestController
@RequestMapping("/sentinel")

public class SentinelController {

    @Resource
    private SentinelService sentinelService;

    @GetMapping("/get")
    public String get() {
        sentinelService.get();
        return "Success";
    }
    @SentinelResource("sentinel")
    @GetMapping("/delete")
    public String delete(){
        System.out.println("delete");
        return "success";
    }
}
