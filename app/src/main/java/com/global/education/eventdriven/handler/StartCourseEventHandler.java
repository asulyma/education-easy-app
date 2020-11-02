package com.global.education.eventdriven.handler;

import com.global.education.eventdriven.EventProcessor;

import lombok.RequiredArgsConstructor;


@RequiredArgsConstructor
public class StartCourseEventHandler {

	private final EventProcessor processor;

	public void handle(Object event) {
		processor.process(event);
	}

}
