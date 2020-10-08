package com.global.education.service;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import com.global.education.controller.dto.SharedLesson;
import com.global.education.model.learning.LessonEntity;
import com.global.education.repository.LessonRepository;


@RunWith(MockitoJUnitRunner.class)
public class LessonServiceTest {

	private static final long TEST_LESSON_ID = 1L;

	@Mock
	private LessonRepository lessonRepository;
	@InjectMocks
	private LessonService testInstance;

	private final LessonEntity lessonEntity = new LessonEntity();

	@Before
	public void init() {
		when(lessonRepository.findById(TEST_LESSON_ID)).thenReturn(Optional.of(lessonEntity));
	}

	@Test
	public void shouldUpdateLessonCorrectly() {
		testInstance.updateLesson(TEST_LESSON_ID, new SharedLesson());
		verify(lessonRepository).findById(TEST_LESSON_ID);
		verify(lessonRepository).save(any());
	}
}
