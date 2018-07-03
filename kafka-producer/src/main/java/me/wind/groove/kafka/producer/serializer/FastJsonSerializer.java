package me.wind.groove.kafka.producer.serializer;

import com.alibaba.fastjson.JSON;
import org.apache.kafka.common.serialization.Serializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;

public class FastJsonSerializer<T> implements Serializer<T> {

    private static final Logger logger = LoggerFactory.getLogger(FastJsonSerializer.class);

    public void configure(Map<String, ?> map, boolean b) {

    }

    public byte[] serialize(String s, T t) {
        byte[] ret = null;
        try {
            ret = JSON.toJSONString(t).getBytes("utf-8");
        } catch (Exception e) {
            logger.warn("Failed to serialize the object: {}", t, e);
        }
        return ret;
    }

    public void close() {

    }
}
