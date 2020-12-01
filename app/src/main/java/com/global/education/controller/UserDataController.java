package com.global.education.controller;

import static com.global.education.mapper.UserMapper.INSTANCE;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import com.global.education.controller.dto.SpecificationRequest;
import com.global.education.controller.dto.User;
import com.global.education.controller.handler.BaseHandler;
import com.global.education.service.UserDataService;


@RestController
@RequestMapping("/users")
public class UserDataController extends BaseHandler {

	@Autowired
	private UserDataService userDataService;

	@GetMapping("/current")
	public User getCurrentUser() {
		return INSTANCE.buildUser(userDataService.findCurrentUser());
	}

	@GetMapping
	@Secured("ROLE_ADMIN")
	public List<User> getAllUsers(@Valid @ModelAttribute SpecificationRequest request) {
		return INSTANCE.buildUsers(userDataService.findAllUsers(request));
	}

}
