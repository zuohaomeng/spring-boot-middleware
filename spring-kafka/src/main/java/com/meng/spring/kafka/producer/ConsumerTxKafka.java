package com.meng.spring.kafka.producer;

import org.apache.kafka.clients.consumer.*;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.TopicPartition;
import org.apache.kafka.common.serialization.IntegerDeserializer;
import org.apache.kafka.common.serialization.IntegerSerializer;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.apache.kafka.common.serialization.StringSerializer;

import java.time.Duration;
import java.util.*;

/**
 * @author ZuoHao
 * @date 2021/5/15
 */
public class ConsumerTxKafka extends Thread {
    private final KafkaConsumer<Integer, String> consumer;
    private final String topic;
    private final KafkaProducer<Integer, String> producer;
    private final String topic2;

    public ConsumerTxKafka(String topic, String topic2) {
        Properties properties = new Properties();
        properties.setProperty(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
        properties.setProperty(ConsumerConfig.GROUP_ID_CONFIG, "spring-kafka-consumer");
        //offset自动提交
        properties.setProperty(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, "false");
        //自动提交间隔时间
        properties.setProperty(ConsumerConfig.AUTO_COMMIT_INTERVAL_MS_CONFIG, "1000");
        properties.setProperty(ConsumerConfig.SESSION_TIMEOUT_MS_CONFIG, "30000");

        properties.setProperty(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, IntegerDeserializer.class.getName());
        properties.setProperty(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
        //对于 当前groupid来说，消息的offset从最早的消息开始消费
        properties.setProperty(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");
        consumer = new KafkaConsumer<>(properties);
        this.topic = topic;
        Properties properties2 = new Properties();
        properties2.setProperty(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
        properties2.setProperty(ProducerConfig.CLIENT_ID_CONFIG, "spring-kafka-producer");
        properties2.setProperty(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, IntegerSerializer.class.getName());
        properties2.setProperty(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        //设置transactional_id
        properties2.setProperty(ProducerConfig.TRANSACTIONAL_ID_CONFIG,"1");
        properties2.setProperty(ProducerConfig.TRANSACTION_TIMEOUT_CONFIG,"30000");
        producer = new KafkaProducer<>(properties2);
        this.topic2 = topic2;

    }


    @Override
    public void run() {
        consumer.subscribe(Collections.singleton(this.topic));
        producer.initTransactions();
        while (true) {
            ConsumerRecords<Integer, String> records =
                    consumer.poll(Duration.ofSeconds(1));
            records.forEach(record -> {
                System.out.println(record.key() + " " + record.value() + " -> offset:" + record.offset()+"----------------");
            });
            if (!records.isEmpty()) {
                Map<TopicPartition, OffsetAndMetadata> offsets = new HashMap<>();
                producer.beginTransaction();

                try {
                    for (TopicPartition partition : records.partitions()) {
                        List<ConsumerRecord<Integer, String>> partitionRecords = records.records(partition);
                        for (ConsumerRecord<Integer, String> record : partitionRecords) {
                            ProducerRecord<Integer, String> producerRecord = new ProducerRecord<>("topic-test", record.key(), record.value());
                            producer.send(producerRecord);
                        }
                        long lastConsumedOffset = partitionRecords.get(partitionRecords.size() - 1).offset();
                        offsets.put(partition, new OffsetAndMetadata(lastConsumedOffset + 1));
                    }
                    //提交消费位移
                    producer.sendOffsetsToTransaction (offsets,"spring-kafka-consumer");
                    System.out.println("----------提交位移了------------");
                    //提交事务
//                    producer.commitTransaction();
                }catch (Exception e){
                    e.printStackTrace();
                }finally {
                    //回滚事务
                    producer.abortTransaction();
                }
            }
        }


    }

    public static void main(String[] args) {
        new ConsumerTxKafka("test", "test2").start();
    }
}
