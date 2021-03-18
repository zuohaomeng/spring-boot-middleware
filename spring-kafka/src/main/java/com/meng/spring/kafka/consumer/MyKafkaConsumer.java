package com.meng.spring.kafka.consumer;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.util.Optional;

/**
 * @author ZuoHao
 * @date 2021/3/18
 */
@Component
public class MyKafkaConsumer {

    @KafkaListener(topics = "kafkatest")
    public void listener(ConsumerRecord<String, String> record) {
        Optional<?> msg = Optional.ofNullable(record.value());
        msg.ifPresent(System.out::println);
    }
}
