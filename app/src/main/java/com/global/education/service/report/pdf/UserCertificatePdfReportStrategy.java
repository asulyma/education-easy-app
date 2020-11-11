package com.global.education.service.report.pdf;

import org.springframework.stereotype.Component;
import org.thymeleaf.context.Context;

import com.global.education.model.UserDataEntity;
import com.global.education.model.learning.CourseEntity;
import com.global.education.service.CourseService;

import lombok.RequiredArgsConstructor;


@Component
@RequiredArgsConstructor
public class UserCertificatePdfReportStrategy extends AbstractPdfReportStrategy {

	private final CourseService courseService;

	@Override
	public String generateFilename() {
		return "Certificate.pdf";
	}

	@Override
	protected Context buildContext(UserDataEntity user, Long courseId) {
		CourseEntity course = courseService.getCourseById(courseId);

		Context context = new Context();
		context.setVariable("username", user.getUsername());
		context.setVariable("courseName", course.getTitle());
		context.setVariable("scope", user.getProgressMap().get(courseId).getProgressValue());
		context.setVariable("passDate", user.getProgressMap().get(courseId).getPassedDate());

		return context;
	}

	@Override
	protected String getTemplateLocation() {
		return "template/user-certificate";
	}
}
