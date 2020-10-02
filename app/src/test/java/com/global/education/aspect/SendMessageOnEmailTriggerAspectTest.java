package com.global.education.aspect;

import static org.mockito.Mockito.*;

import java.lang.annotation.Annotation;
import java.util.UUID;

import javax.mail.internet.MimeMessage;

import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.test.util.ReflectionTestUtils;

import com.education.common.kafka.dto.UserFinishLessonEvent;
import com.education.common.kafka.dto.UserStartCourseEvent;
import com.education.common.model.EmailType;
import com.global.education.config.TranslationHolder;
import com.global.education.model.UserDataEntity;
import com.global.education.model.learning.CourseEntity;
import com.global.education.service.CourseService;
import com.global.education.service.UserDataService;


@RunWith(MockitoJUnitRunner.class)
public class SendMessageOnEmailTriggerAspectTest {

	private static final UUID USER_UUID = UUID.randomUUID();
	private static final String EMAIL = "qqq@dot.net";
	private static final String TEST_VALUE = "0000000";
	private static final long COURSE_ID = 1;

	@InjectMocks
	private SendMessageOnEmailTriggerAspect testInstance;
	@Mock
	private JavaMailSender javaMailSender;
	@Mock
	private UserDataService userDataService;
	@Mock
	private TranslationHolder translationHolder;
	@Mock
	private ProceedingJoinPoint proceedingJoinPoint;
	@Mock
	private MimeMessage message;
	@Mock
	private CourseService courseService;

	private final UserDataEntity user = new UserDataEntity();
	private final CourseEntity course = new CourseEntity();

	@Before
	public void setUp() throws Throwable {
		ReflectionTestUtils.setField(testInstance, "isEnable", true);
		testInstance.init();

		user.setEmail(EMAIL);
		course.setTitle(TEST_VALUE);

		when(proceedingJoinPoint.proceed()).thenReturn(new Object());
		when(javaMailSender.createMimeMessage()).thenReturn(message);
		when(userDataService.findUser(USER_UUID)).thenReturn(user);
		when(translationHolder.getStartCourseMessage()).thenReturn(StringUtils.EMPTY);
		when(translationHolder.getFinishLessonMessage()).thenReturn(StringUtils.EMPTY);
		when(courseService.getCourseById(COURSE_ID)).thenReturn(course);
	}

	@Test
	public void shouldCreateEmailOfStartCourseCorrectly() throws Throwable {
		TriggerSendEmail annotation = buildAnnotation(EmailType.START_COURSE);
		UserStartCourseEvent startEvent = new UserStartCourseEvent();
		startEvent.setUserUuid(USER_UUID);
		startEvent.setCourseId(COURSE_ID);

		testInstance.processingAspect(proceedingJoinPoint, annotation, startEvent);

		verify(javaMailSender).createMimeMessage();
		verify(userDataService).findUser(USER_UUID);
		verify(translationHolder).getStartCourseMessage();
	}

	@Test
	public void shouldCreateEmailOfFinishCourseCorrectly() throws Throwable {
		TriggerSendEmail annotation = buildAnnotation(EmailType.FINISH_LESSON);
		UserFinishLessonEvent finishEvent = new UserFinishLessonEvent();
		finishEvent.setUserUuid(USER_UUID);
		finishEvent.setCourseId(COURSE_ID);

		testInstance.processingAspect(proceedingJoinPoint, annotation, finishEvent);

		verify(javaMailSender).createMimeMessage();
		verify(userDataService).findUser(USER_UUID);
		verify(translationHolder).getFinishLessonMessage();
	}

	@Test
	public void shouldThrowExceptionWhenTypeIsUnknown() throws Throwable {
		TriggerSendEmail annotation = buildAnnotation(null);
		UserFinishLessonEvent finishEvent = new UserFinishLessonEvent();
		finishEvent.setUserUuid(USER_UUID);

		testInstance.processingAspect(proceedingJoinPoint, annotation, finishEvent);

		verify(javaMailSender).createMimeMessage();
		verify(userDataService, never()).findUser(USER_UUID);
	}

	private TriggerSendEmail buildAnnotation(EmailType type) {
		return new TriggerSendEmail() {
			@Override
			public EmailType target() {
				return type;
			}

			@Override
			public Class<? extends Annotation> annotationType() {
				return TriggerSendEmail.class;
			}
		};
	}

}
