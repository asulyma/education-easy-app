package com.global.education.kafka.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.education.common.kafka.dto.UserFinishLessonEvent;
import com.education.common.kafka.dto.UserStartCourseEvent;
import com.education.common.model.EmailType;
import com.global.education.aspect.TriggerSendEmail;
import com.global.education.kafka.producer.UserFinishLessonEventProducer;
import com.global.education.kafka.producer.UserStartCourseEventProducer;

import lombok.SneakyThrows;


@Service
public class UserUpdateEventKafkaService {

	@Autowired
	private UserFinishLessonEventProducer userFinishLessonEventProducer;
	@Autowired
	private UserStartCourseEventProducer userStartCourseEventProducer;

	@SneakyThrows
	@TriggerSendEmail(target = EmailType.START_COURSE)
	public void sendStartCourseEvent(UserStartCourseEvent dto) {
		userStartCourseEventProducer.sendMessage(dto);
	}

	@SneakyThrows
	@TriggerSendEmail(target = EmailType.FINISH_LESSON)
	public void sendFinishLessonEvent(UserFinishLessonEvent dto) {
		userFinishLessonEventProducer.sendMessage(dto);
	}

}
