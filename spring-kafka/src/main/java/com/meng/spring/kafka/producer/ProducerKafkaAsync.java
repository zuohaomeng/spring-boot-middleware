package com.meng.spring.kafka.producer;

import org.apache.kafka.clients.producer.*;
import org.apache.kafka.common.serialization.IntegerSerializer;
import org.apache.kafka.common.serialization.StringSerializer;

import java.util.Properties;
import java.util.concurrent.TimeUnit;

/**
 * @author ZuoHao
 * @date 2021/3/18
 */
public class ProducerKafkaAsync extends Thread {
    private final KafkaProducer<Integer, String> producer;
    private final String topic;

    public ProducerKafkaAsync(String topic) {
        Properties properties = new Properties();
        properties.setProperty(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
        properties.setProperty(ProducerConfig.CLIENT_ID_CONFIG, "spring-kafka-producer");
        properties.setProperty(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, IntegerSerializer.class.getName());
        properties.setProperty(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        producer = new KafkaProducer<>(properties);
        this.topic = topic;

    }

    @Override
    public void run() {
        int num = 0;
        while (num < 30) {
            String msg = "kafka test message:" + num;
            try {
                producer.send(new ProducerRecord<Integer, String>(topic, msg), new Callback() {
                    @Override
                    public void onCompletion(RecordMetadata recordMetadata, Exception e) {
                        System.out.println("callback:" + recordMetadata.offset() + "->" + recordMetadata.partition());
                    }
                });
                TimeUnit.SECONDS.sleep(2);
                num++;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        System.out.println("spring-kafka-consumer".hashCode()%50);
        int s = Math.abs("test".hashCode())%50;
        System.out.println(s);
//        new ProducerKafkaAsync("test").start();
    }
}
