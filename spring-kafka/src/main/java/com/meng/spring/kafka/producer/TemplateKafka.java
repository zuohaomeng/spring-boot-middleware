package com.meng.spring.kafka.producer;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @author ZuoHao
 * @date 2021/3/18
 */
@Component
public class TemplateKafka {
    @Resource
    private KafkaTemplate<String, String> kafkaTemplate;


    public void send() {
        kafkaTemplate.send("kafkatest", "msgKey", "msgData");
    }
}
