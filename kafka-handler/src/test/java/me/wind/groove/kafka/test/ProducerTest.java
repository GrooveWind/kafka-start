package me.wind.groove.kafka.test;

import me.wind.groove.kafka.common.entity.OrderInfo;
import org.apache.kafka.clients.producer.*;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class ProducerTest {

    /**
     * 基础消息发送
     */
    @Test
    public void testProducerSendMsg(){
        Properties props = new Properties();
        /** broker服务器地址-必须配置 */
        props.put("bootstrap.servers", "10.1.50.72:9092");
        /** key序列化器-必须配置 */
        props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        /** value序列化器-必须配置 */
        props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");

        warpCommonProps(props);

        Producer<String, String> producer = null;
        try {
            producer = new KafkaProducer<>(props);
            for (int i = 0; i < 10; i++) {
                producer.send(new ProducerRecord<>("helloKafka", "hello" + i, i * 100 + ""), new Callback(){
                    public void onCompletion(RecordMetadata recordMetadata, Exception e) {
                        if (e != null) {
                            System.err.println(e.getMessage());
                        } else {
                            System.out.println(recordMetadata.toString());
                        }
                    }
                });
            }
        } finally {
            if (producer != null) {
                producer.close();
            }
        }
    }

    /**
     * 自定义序列化发送
     */
    @Test
    public void testProducerUseSelfSerializer(){
        Properties props = new Properties();
        /** broker服务器地址-必须配置 */
        props.put("bootstrap.servers", "10.1.50.72:9092");
        /** key序列化器-必须配置 */
        props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        /** value序列化器-必须配置 */
        props.put("value.serializer", "me.wind.groove.kafka.producer.serializer.FastJsonSerializer");

        warpCommonProps(props);

        Producer<String, OrderInfo> producer = null;
        try {
            producer = new KafkaProducer<>(props);

            for (int i = 0; i < 10; i++) {
                OrderInfo order = new OrderInfo();
                order.setId(30000l + i);
                order.setOrderId("reg-order-" + i);
                order.setVersion(0);

                producer.send(new ProducerRecord<>("orderSet", order.getOrderId(), order), new Callback(){
                    public void onCompletion(RecordMetadata recordMetadata, Exception e) {
                        if (e != null) {
                            System.err.println(e.getMessage());
                        } else {
                            System.out.println(recordMetadata.toString());
                        }
                    }
                });
            }
        } finally {
            if (producer != null) {
                producer.close();
            }
        }
    }

    /**
     * 自定义拦截链发送
     */
    @Test
    public void testProducerWithInterceptor(){
        Properties props = new Properties();
        /** broker服务器地址-必须配置 */
        props.put("bootstrap.servers", "10.1.50.72:9092");
        /** key序列化器-必须配置 */
        props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        /** value序列化器-必须配置 */
        props.put("value.serializer", "me.wind.groove.kafka.producer.serializer.FastJsonSerializer");

        List<String> interceptors = new ArrayList<>(2);
        interceptors.add("me.wind.groove.kafka.producer.interceptor.TimestampPrependerInterceptor");
        interceptors.add("me.wind.groove.kafka.producer.interceptor.CounterPrependerInterceptor");
        props.put("interceptor.classes", interceptors);

        warpCommonProps(props);

        Producer<String, OrderInfo> producer = null;
        try {
            producer = new KafkaProducer<>(props);

            for (int i = 0; i < 10; i++) {
                OrderInfo order = new OrderInfo();
                order.setId(30000l + i);
                order.setOrderId("TxOrderNum-" + i);
                order.setVersion(0);

                producer.send(new ProducerRecord<>("helloKafka", order.getOrderId(), order), new Callback(){
                    public void onCompletion(RecordMetadata recordMetadata, Exception e) {
                        if (e != null) {
                            System.err.println(e.getMessage());
                        } else {
                            System.out.println(recordMetadata.toString());
                        }
                    }
                });
            }
        } finally {
            if (producer != null) {
                producer.close();
            }
        }
    }

    /**
     * 公共参数封装
     * @param props
     */
    private void warpCommonProps(Properties props) {
        /** -1-消息不会丢  0-不回调 消息可能丢  1-消息写入日志后回调*/
        props.put("acks", "-1");
        /** 压缩算法: gzip snappy lz4 */
        props.put("compression.type", "lz4");
        /** 消息发送异常 自动重试次数 */
        props.put("retries", 3);
        /** batch大小可以影响到kafka吞吐量 */
        props.put("batch.size", 323840);
        /** 控制消息发送延时行为 */
        props.put("linger.ms", 100);
        /** 缓冲区大小 默认32MB(33554432) */
        props.put("buffer.memory", 33554432);
        /** 控制producer端能够发送的最大消息大小 */
        props.put("max.request.size", 10485760);
        /** broker返还处理结果给producer的超时时间 */
        props.put("request.timeout.ms", 60000);
        /** 控制send()的阻塞时长 */
        props.put("max.block.ms", 3000);
    }

}
