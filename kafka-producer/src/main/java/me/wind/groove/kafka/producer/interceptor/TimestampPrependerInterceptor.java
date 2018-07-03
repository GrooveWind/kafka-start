package me.wind.groove.kafka.producer.interceptor;

import me.wind.groove.kafka.common.entity.OrderInfo;
import org.apache.kafka.clients.producer.ProducerInterceptor;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;

import java.util.Map;

public class TimestampPrependerInterceptor implements ProducerInterceptor<String, OrderInfo> {

    public ProducerRecord<String, OrderInfo> onSend(ProducerRecord<String, OrderInfo> record) {
        OrderInfo orderInfo = record.value();
        orderInfo.setSendTime(System.currentTimeMillis());

        return new ProducerRecord<String, OrderInfo>(record.topic(), record.partition(), record.timestamp(),
                record.key(), orderInfo);
    }

    public void onAcknowledgement(RecordMetadata recordMetadata, Exception e) {

    }

    public void close() {

    }

    public void configure(Map<String, ?> map) {

    }
}
