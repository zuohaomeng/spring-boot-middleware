package com.meng.spring.kafka.interceptor;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.IntegerSerializer;
import org.apache.kafka.common.serialization.StringSerializer;

import java.util.Properties;

/**
 * @author ZuoHao
 * @date 2021/5/19
 */
public class InterceptorProducer {

    public static void main(String[] args) {
        Integer i = 4444;
        Long j = 4444L;
        System.out.println(i.equals(j));
    }
}
