package com.meng.spring.kafka.controller;

import com.meng.spring.kafka.producer.TemplateKafka;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author ZuoHao
 * @date 2021/3/18
 */
@RestController
@RequestMapping("/kafka")
public class KafkaController {
    @Resource
    private TemplateKafka templateKafka;

    @GetMapping("/send")
    public String send() {
        templateKafka.send();
        return "success";
    }
}
