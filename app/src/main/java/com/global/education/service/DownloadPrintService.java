package com.global.education.service;

import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Service;

import com.global.education.model.UserDataEntity;
import com.global.education.service.report.csv.CsvReportStrategy;
import com.global.education.service.report.pdf.PdfReportStrategy;

import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;


@Service
@RequiredArgsConstructor
public class DownloadPrintService {

	private final UserDataService userDataService;
	private final CsvReportStrategy csvReportStrategy;
	private final PdfReportStrategy pdfReportStrategy;

	public void downloadUsers(List<UUID> userUuids, String contentType, HttpServletResponse response) {
		String report = csvReportStrategy.generate(userUuids);
		String filename = csvReportStrategy.generateFilename();

		writeReportFormat(report.getBytes(), contentType, filename, response);
	}

	public void downloadCertificate(Long courseId, String contentType, HttpServletResponse response) {
		UserDataEntity user = userDataService.findCurrentUser();

		byte[] report = pdfReportStrategy.generate(user, courseId);
		String filename = pdfReportStrategy.generateFilename();

		writeReportFormat(report, contentType, filename, response);
		response.setContentLength(report.length);
	}

	@SneakyThrows
	public void writeReportFormat(byte[] report, String contentType, String filename, HttpServletResponse response) {
		response.setContentType(contentType);
		response.setHeader("Content-Disposition", "attachment; filename=" + filename);
		response.getOutputStream().write(report);
		response.getOutputStream().flush();
	}

}
