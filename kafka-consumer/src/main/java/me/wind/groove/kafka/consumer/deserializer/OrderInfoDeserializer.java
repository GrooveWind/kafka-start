package me.wind.groove.kafka.consumer.deserializer;

import com.alibaba.fastjson.JSONObject;
import me.wind.groove.kafka.common.entity.OrderInfo;
import org.apache.kafka.common.serialization.Deserializer;

import java.util.Map;

public class OrderInfoDeserializer implements Deserializer<OrderInfo> {

    public void configure(Map<String, ?> map, boolean b) {

    }

    public OrderInfo deserialize(String s, byte[] bytes) {
        OrderInfo result = null;
        try {
            result = JSONObject.parseObject(bytes, OrderInfo.class);
        } catch (Exception e) {
            System.err.println("反序列化出错, key:[" + s + "]");
        }
        return result;
    }

    public void close() {

    }
}
