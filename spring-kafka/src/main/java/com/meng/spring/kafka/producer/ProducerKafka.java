package com.meng.spring.kafka.producer;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.IntegerSerializer;

import java.util.Properties;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

/**
 * @author ZuoHao
 * @date 2021/3/18
 */
public class ProducerKafka extends Thread {

    private final KafkaProducer<Integer, String> producer;
    private final String topic;

    public ProducerKafka(String topic) {
        Properties properties = new Properties();
        properties.setProperty(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
        properties.setProperty(ProducerConfig.CLIENT_ID_CONFIG, "spring-kafka-producer");
        properties.setProperty(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, IntegerSerializer.class.getName());
        properties.setProperty(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, String.class.getName());
        producer = new KafkaProducer<>(properties);
        this.topic = topic;

    }

    @Override
    public void run() {
        int num = 0;
        while (num < 30) {
            String msg = "kafka test message:" + num;
            try {
                producer.send(new ProducerRecord<Integer, String>(topic, msg)).get();
                TimeUnit.SECONDS.sleep(2);
                num++;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        new ProducerKafka("test").start();
    }
}
