package me.wind.groove.kafka.spring.wrapper.producer;

import me.wind.groove.kafka.spring.wrapper.entity.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
public class KafkaProducer {

    @Autowired
    KafkaTemplate<String, String> kafkaTemplate;

    public void send(int idx){
        Message message = new Message();
        message.setId(1000l + idx);
        message.setBody("Hello...");
        message.setSendTime(System.currentTimeMillis());

        kafkaTemplate.send("helloKafka", message.toString());
    }

}
