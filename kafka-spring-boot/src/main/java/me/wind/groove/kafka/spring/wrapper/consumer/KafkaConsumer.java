package me.wind.groove.kafka.spring.wrapper.consumer;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class KafkaConsumer {

    @KafkaListener(topics = {"helloKafka"})
    public void consume(ConsumerRecord<String, String> record){
        Optional<?> kafkaMsg = Optional.ofNullable(record.value());
        if (kafkaMsg.isPresent()) {
            System.out.println("消费消息:" + kafkaMsg.get());
        }
    }

}
