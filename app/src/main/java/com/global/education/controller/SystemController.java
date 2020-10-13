package com.global.education.controller;

import static com.global.education.mapper.UserMapper.INSTANCE;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import com.global.education.controller.dto.User;
import com.global.education.controller.handler.BaseHandler;
import com.global.education.service.UserDataService;


@RestController
@RequestMapping("/system")
public class SystemController extends BaseHandler {

	@Autowired
	private UserDataService userDataService;

	/**
	 * This method implies that the user has already been registered in the OAuth2 server
	 */
	@PostMapping("/second-step-register")
	public ResponseEntity<HttpStatus> register(@RequestBody @Valid User user) {
		userDataService.createOrUpdateUserData(user);
		return new ResponseEntity<>(HttpStatus.CREATED);
	}

	@GetMapping("/user")
	public User getCurrentUser() {
		return INSTANCE.buildUser(userDataService.findCurrentUser());
	}

	@GetMapping("/users")
	@Secured("ROLE_ADMIN")
	public List<User> getAllUsers() {
		return INSTANCE.buildUsers(userDataService.findAllUsers());
	}

}
