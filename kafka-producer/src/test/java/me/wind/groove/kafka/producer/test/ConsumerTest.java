package me.wind.groove.kafka.producer.test;

import me.wind.groove.kafka.entity.OrderInfo;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.clients.producer.*;
import org.junit.Test;

import java.util.*;

public class ConsumerTest {

    /**
     * 基础消息消费
     */
    @Test
    public void testConsumeMsg(){
        Properties props = new Properties();
        props.put("bootstrap.servers", "10.1.50.72:9092");
        props.put("group.id", "helloGroup");
        props.put("enable.auto.commit", "true");
        props.put("auto.commit.interval.ms", "1000");
        props.put("auto.offset.reset", "earliest");
        props.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        props.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");

        KafkaConsumer<String, String> consumer = new KafkaConsumer<>(props);
        consumer.subscribe(Arrays.asList("helloKafka"));

        System.err.println("start...");
        try {
            while (true) {
                ConsumerRecords<String, String> records = consumer.poll(1000);
                for (ConsumerRecord<String, String> record : records) {
                    System.out.printf("offset = %d, key = %s, value = %s%n", record.offset(), record.key(), record.value());
                }
            }
        } catch (Exception e) {
            System.err.println(e.getMessage());
        } finally {
            System.err.println("end...");
            consumer.close();
        }
    }

    @Test
    public void testConsumeMsgUseSelfDeserializer(){
        Properties props = new Properties();
        props.put("bootstrap.servers", "10.1.50.72:9092");
        props.put("group.id", "orderGroup");
        props.put("enable.auto.commit", "true");
        props.put("auto.commit.interval.ms", "1000");
        props.put("auto.offset.reset", "earliest");
        props.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        props.put("value.deserializer", "me.wind.groove.kafka.consumer.deserializer.OrderInfoDeserializer");

        KafkaConsumer<String, OrderInfo> consumer = new KafkaConsumer<>(props);
        consumer.subscribe(Arrays.asList("orderSet"));

        System.err.println("start...");
        try {
            while (true) {
                ConsumerRecords<String, OrderInfo> records = consumer.poll(1000);
                for (ConsumerRecord<String, OrderInfo> record : records) {
                    OrderInfo orderInfo = record.value();
                    System.out.printf("offset = %d, key = %s, orderId = %s, version = %s%n", record.offset(), record.key(), orderInfo.getOrderId(), orderInfo.getVersion());
                }
            }
        } catch (Exception e) {
            System.err.println(e.getMessage());
        } finally {
            System.err.println("end...");
            consumer.close();
        }
    }

}
