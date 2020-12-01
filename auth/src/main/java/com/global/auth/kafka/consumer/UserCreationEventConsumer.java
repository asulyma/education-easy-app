package com.global.auth.kafka.consumer;


import static com.education.common.utils.JacksonUtils.toObject;

import java.util.Map;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;

import com.education.common.dto.event.UserCreationEvent;

import lombok.extern.slf4j.Slf4j;


@Slf4j
public class UserCreationEventConsumer extends EventConsumer {

	public UserCreationEventConsumer(Map<String, Object> configs, String topic) {
		super(configs, topic);
	}

	@SuppressWarnings("unchecked")
	@KafkaListener(topics = "education-user-creation", groupId = "education-user-creation-group")
	public void listen(Object message) {
		if (validate(message)) {
			log.warn("Empty message from {}", topic);
			return;
		}
		ConsumerRecord<String, String> record = (ConsumerRecord<String, String>) message;
		userService.createUser(toObject(record.value(), UserCreationEvent.class));
	}

}
