package me.wind.groove.kafka.spring.wrapper;

import me.wind.groove.kafka.spring.wrapper.producer.KafkaProducer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class KafkaApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(KafkaApplication.class, args);
        KafkaProducer producer = context.getBean(KafkaProducer.class);
        for (int i = 0; i < 5; i++) {
            // 调用消息发送类中的消息发送方法
            producer.send(i);
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                System.err.println(e.getMessage());
            }
        }
    }

}
