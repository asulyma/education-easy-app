package com.global.education.service.report.pdf;

import com.global.education.model.UserDataEntity;


public interface PdfReportStrategy {

	byte[] generate(UserDataEntity userDataEntity, Long courseId);

	String generateFilename();

}
