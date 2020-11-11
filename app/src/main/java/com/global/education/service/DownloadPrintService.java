package com.global.education.service;

import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Service;

import com.global.education.service.report.CsvReportStrategy;

import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;


@Service
@RequiredArgsConstructor
public class DownloadPrintService {

	private final CsvReportStrategy csvReportStrategy;

	public void downloadUsers(List<UUID> userUuids, String contentType, HttpServletResponse response) {
		String report = csvReportStrategy.generate(userUuids);
		String filename = csvReportStrategy.generateFilename();

		writeReportFormat(report.getBytes(), contentType, filename, response);
	}

	@SneakyThrows
	public void writeReportFormat(byte[] report, String contentType, String filename, HttpServletResponse response) {
		response.setContentType(contentType);
		response.setHeader("Content-Disposition", "attachment; filename=" + filename);
		response.getOutputStream().write(report);
		response.getOutputStream().flush();
	}

}
