package me.wind.groove.kafka.consumer.deserializer;

import com.alibaba.fastjson.JSONObject;
import me.wind.groove.kafka.entity.OrderInfo;
import org.apache.kafka.common.serialization.Deserializer;

import java.util.Map;

public class OrderInfoDeserializer implements Deserializer<OrderInfo> {

    @Override
    public void configure(Map<String, ?> map, boolean b) {

    }

    @Override
    public OrderInfo deserialize(String s, byte[] bytes) {
        OrderInfo result = null;
        try {
            result = JSONObject.parseObject(bytes, OrderInfo.class);
        } catch (Exception e) {
            System.err.println("反序列化字符串[" + s + "]出错");
        }
        return result;
    }

    @Override
    public void close() {

    }
}
