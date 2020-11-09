package com.global.education.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
@ConfigurationProperties(prefix = "course")
public class TranslationHolder {

	private String startCourseMessage;
	private String finishLessonMessage;
	private String finishCourseMessage;

}
