package com.global.education.aspect;

import static java.lang.String.format;

import java.util.EnumMap;
import java.util.Map;
import java.util.function.BiConsumer;

import javax.annotation.PostConstruct;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import com.education.common.kafka.dto.UserFinishLessonEvent;
import com.education.common.kafka.dto.UserStartCourseEvent;
import com.education.common.model.EmailType;
import com.global.education.config.TranslationHolder;
import com.global.education.model.UserDataEntity;
import com.global.education.service.UserDataService;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;


@Slf4j
@Aspect
@Component
public class SendMessageOnEmailTriggerAspect {

	private static final Map<EmailType, BiConsumer<MimeMessageHelper, Object>> TYPE = new EnumMap<>(EmailType.class);

	@Autowired
	private JavaMailSender javaMailSender;
	@Autowired
	private UserDataService userDataService;
	@Autowired
	private TranslationHolder translationHolder;

	@Value("${spring.mail.enabled}")
	private boolean isEnable;

	@PostConstruct
	public void init() {
		TYPE.put(EmailType.START_COURSE, this::buildStartCourseEmail);
		TYPE.put(EmailType.FINISH_LESSON, this::buildFinishLessonEmail);
	}

	@Around("@annotation(annotation) && args(event)")
	public Object processingAspect(ProceedingJoinPoint point, TriggerSendEmail annotation, Object event)
			throws Throwable {
		Object proceed = point.proceed();
		if (isEnable) {
			sendEmailNotification(annotation, event);
		}
		return proceed;
	}

	private void sendEmailNotification(TriggerSendEmail annotation, Object event) {
		try {
			MimeMessage message = buildEmailMessage(annotation, event);
			javaMailSender.send(message);
		} catch (MessagingException e) {
			log.error("Failed while sending message! {}", e.getMessage());
		}
	}

	private MimeMessage buildEmailMessage(TriggerSendEmail annotation, Object event) throws MessagingException {
		MimeMessage message = javaMailSender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(message, true);

		if (TYPE.containsKey(annotation.target())) {
			TYPE.get(annotation.target()).accept(helper, event);
		} else {
			throw new MessagingException("Unknown type of email sender");
		}

		return message;
	}

	@SneakyThrows
	private void buildStartCourseEmail(MimeMessageHelper helper, Object objEvent) {
		UserStartCourseEvent event = (UserStartCourseEvent) objEvent;
		UserDataEntity user = userDataService.findUser(event.getUserUuid());
		String text = translationHolder.getStartCourseMessage();

		helper.setTo(user.getEmail());
		helper.setSubject("Education Application: Start Course");
		helper.setText(format(text, event.getCourseId()));
	}

	@SneakyThrows
	private void buildFinishLessonEmail(MimeMessageHelper helper, Object objEvent) {
		UserFinishLessonEvent event = (UserFinishLessonEvent) objEvent;
		UserDataEntity user = userDataService.findUser(event.getUserUuid());
		String text = translationHolder.getFinishLessonMessage();

		helper.setTo(user.getEmail());
		helper.setSubject("Education Application: Finish Lesson");
		helper.setText(format(text, event.getAlreadyDoneLesson(), event.getCourseId()));
	}

}
