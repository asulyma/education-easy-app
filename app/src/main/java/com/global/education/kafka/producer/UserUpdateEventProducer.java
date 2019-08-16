package com.global.education.kafka.producer;

import com.global.education.util.JacksonUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;

import java.util.Map;
import java.util.concurrent.TimeUnit;

@Slf4j
public class UserUpdateEventProducer extends KafkaProducer<String, String> {

    private static final int TIMEOUT_PERIOD = 30;
    private String topic;

    public UserUpdateEventProducer(Map<String, Object> configs) {
        super(configs);
    }

    public UserUpdateEventProducer(Map<String, Object> configs, String topic) {
        super(configs);
        this.topic = topic;
    }

    public void sendEvent(UserUpdateEventDto message) throws Exception {
        String payload = JacksonUtil.toJsonString(message);
        ProducerRecord<String, String> record = new ProducerRecord<>(topic, payload);
        sendMessage(record);
    }

    private void sendMessage(ProducerRecord<String, String> record) throws Exception {
        this.send(record).get(TIMEOUT_PERIOD, TimeUnit.SECONDS);
    }
}
