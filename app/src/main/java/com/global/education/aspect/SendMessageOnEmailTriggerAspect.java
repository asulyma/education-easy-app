package com.global.education.aspect;

import com.education.common.kafka.dto.UserFinishLessonEvent;
import com.education.common.kafka.dto.UserStartCourseEvent;
import com.global.education.config.TranslationHolder;
import com.global.education.service.UserDataService;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

@Slf4j
@Aspect
@Component
public class SendMessageOnEmailTriggerAspect {

    @Autowired
    private JavaMailSender javaMailSender;

    @Autowired
    private UserDataService userDataService;

    @Autowired
    private TranslationHolder translationHolder;

    @Value("${spring.mail.enabled}")
    private boolean isEnable;

    @Around("@annotation(annotation) && args(event)")
    public Object processingAspect(ProceedingJoinPoint point, TriggerSendEmail annotation, Object event) throws Throwable {
        Object proceed = point.proceed();
        if (isEnable) {
            sendEmailNotification(event);
        }
        return proceed;
    }

    //TODO refactor it to better solution
    private void sendEmailNotification(Object event) {
        try {
            MimeMessage mimeMessage = javaMailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);

            if (event instanceof UserStartCourseEvent) {
                buildStartCourseEmail(helper, (UserStartCourseEvent) event);
            } else if (event instanceof UserFinishLessonEvent) {
                buildFinishLessonEmail(helper, (UserFinishLessonEvent) event);
            } else {
                throw new MessagingException("Unknown type of email sender");
            }

            javaMailSender.send(mimeMessage);
        } catch (MessagingException e) {
            log.error("Failed while sending message! {}", e.getMessage());
        }
    }

    private void buildStartCourseEmail(MimeMessageHelper helper, UserStartCourseEvent event) throws MessagingException {
        helper.setTo(userDataService.findUser(event.getUserUuid()).getEmail());
        helper.setSubject("Education Application: Start Course");
        helper.setText(translationHolder.getStartCourseMessage().replaceAll("##", event.getCourseId().toString()));
    }

    private void buildFinishLessonEmail(MimeMessageHelper helper, UserFinishLessonEvent event) throws MessagingException {
        helper.setTo(userDataService.findUser(event.getUserUuid()).getEmail());
        helper.setSubject("Education Application: Finish Lesson");
        String text = translationHolder.getFinishLessonMessage();
        helper.setText(text
                .replaceAll("#", event.getAlreadyDoneLesson().toString())
                .replaceAll("##", event.getCourseId().toString()));
    }

}
