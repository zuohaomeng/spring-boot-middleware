package com.meng.spring.kafka.producer;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.IntegerDeserializer;
import org.apache.kafka.common.serialization.IntegerSerializer;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.apache.kafka.common.serialization.StringSerializer;

import java.time.Duration;
import java.util.Collections;
import java.util.Properties;

/**
 * @author ZuoHao
 * @date 2021/3/18
 */
public class ConsumerKafka extends Thread {
    private final KafkaConsumer<Integer, String> consumer;
    private final String topic;

    public ConsumerKafka(String topic) {
        Properties properties = new Properties();
        properties.setProperty(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
        properties.setProperty(ConsumerConfig.GROUP_ID_CONFIG, "spring-kafka-consumer");
        //offset自动提交
        properties.setProperty(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, "true");
        //自动提交间隔时间
        properties.setProperty(ConsumerConfig.AUTO_COMMIT_INTERVAL_MS_CONFIG, "1000");
        properties.setProperty(ConsumerConfig.SESSION_TIMEOUT_MS_CONFIG, "30000");

        properties.setProperty(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, IntegerDeserializer.class.getName());
        properties.setProperty(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
        //对于 当前groupid来说，消息的offset从最早的消息开始消费
        properties.setProperty(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");
        consumer = new KafkaConsumer<>(properties);
        this.topic = topic;
    }

    @Override
    public void run() {
        while (true) {
            consumer.subscribe(Collections.singleton(this.topic));
            ConsumerRecords<Integer, String> records =
                    consumer.poll(Duration.ofSeconds(1));
            records.forEach(record -> {
                System.out.println(record.key() + " " + record.value() + " -> offset:" + record.offset());
            });
        }
    }

    public static void main(String[] args) {
        new ConsumerKafka("test").start();
    }
}
