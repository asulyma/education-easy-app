package com.global.education.controller;

import static java.lang.String.format;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import com.education.common.dto.event.UserCreationEvent;
import com.global.education.controller.dto.User;
import com.global.education.controller.handler.BaseHandler;
import com.global.education.service.*;

import lombok.RequiredArgsConstructor;


@RestController
@RequestMapping("/system")
@RequiredArgsConstructor
public class SystemController extends BaseHandler {

	private static final String SEND_USER_CREATION = "UserCreationEvent for %s has been sent to OAuth microservice";

	private final UserDataService userDataService;
	private final CourseService courseService;
	private final EventService eventService;

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

	@PostMapping("/create-user")
	public ResponseEntity<String> createUser(@RequestBody @Valid UserCreationEvent user) {
		eventService.sendUserCreationEvent(user);
		return ResponseEntity.status(HttpStatus.CREATED).body(format(SEND_USER_CREATION, user.getUsername()));
	}

}
