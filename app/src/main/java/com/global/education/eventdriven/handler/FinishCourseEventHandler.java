package com.global.education.eventdriven.handler;

import com.global.education.eventdriven.EventProcessor;

import lombok.RequiredArgsConstructor;


@RequiredArgsConstructor
public class FinishCourseEventHandler {

	private final EventProcessor processor;

	public void handle(Object event) {
		processor.process(event);
	}
}
