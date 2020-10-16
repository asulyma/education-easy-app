package com.global.education.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import com.global.education.controller.dto.User;
import com.global.education.controller.handler.BaseHandler;
import com.global.education.service.CourseService;
import com.global.education.service.UserDataService;


@RestController
@RequestMapping("/system")
public class SystemController extends BaseHandler {

	@Autowired
	private UserDataService userDataService;
	@Autowired
	private CourseService courseService;

	/**
	 * This method implies that the user has already been registered in the OAuth2 server
	 */
	@PostMapping("/second-step-register")
	public ResponseEntity<HttpStatus> register(@RequestBody @Valid User user) {
		userDataService.createOrUpdateUserData(user);
		return new ResponseEntity<>(HttpStatus.CREATED);
	}

	@PostMapping("/invalidate-cache")
	@Secured("ROLE_ADMIN")
	public ResponseEntity<HttpStatus> invalidateCache() {
		courseService.destroy();
		return ResponseEntity.noContent().build();
	}

}
