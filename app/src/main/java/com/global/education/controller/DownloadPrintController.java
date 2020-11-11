package com.global.education.controller;

import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletResponse;

import org.springframework.http.MediaType;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import com.global.education.controller.handler.BaseHandler;
import com.global.education.service.DownloadPrintService;
import com.global.education.service.ValidationService;

import lombok.RequiredArgsConstructor;


@RestController
@RequestMapping("/download")
@RequiredArgsConstructor
public class DownloadPrintController extends BaseHandler {

	private final DownloadPrintService downloadPrintService;
	private final ValidationService validationService;

	@GetMapping("/users")
	@Secured("ROLE_ADMIN")
	public void downloadUsers(@RequestParam List<UUID> userUuids, HttpServletResponse response) {
		downloadPrintService.downloadUsers(userUuids, "application/ms-excel", response);
	}

	@GetMapping("/certificate")
	public void downloadCertificate(@RequestParam Long courseId, HttpServletResponse response) {
		validationService.checkUserOnFinishCourse(courseId);
		downloadPrintService.downloadCertificate(courseId, MediaType.APPLICATION_PDF_VALUE, response);
	}

}
