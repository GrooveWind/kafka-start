package me.wind.groove.kafka.producer.interceptor;

import me.wind.groove.kafka.entity.OrderInfo;
import org.apache.kafka.clients.producer.ProducerInterceptor;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;

import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

public class CounterPrependerInterceptor implements ProducerInterceptor<String, OrderInfo> {

    private static AtomicInteger succCounter = new AtomicInteger(0);
    private static AtomicInteger errorCounter = new AtomicInteger(0);

    public ProducerRecord<String, OrderInfo> onSend(ProducerRecord<String, OrderInfo> record) {
        return record;
    }

    public void onAcknowledgement(RecordMetadata recordMetadata, Exception e) {
        if (e == null) {
            succCounter.incrementAndGet();
        } else {
            errorCounter.incrementAndGet();
        }
    }

    public void close() {
        System.out.println("Success send num: " + succCounter.get());
        System.out.println("Error send num: " + errorCounter.get());
    }

    public void configure(Map<String, ?> map) {

    }
}
