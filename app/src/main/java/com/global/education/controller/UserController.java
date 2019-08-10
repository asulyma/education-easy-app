package com.global.education.controller;

import com.global.education.controller.dto.SpecificationRequest;
import com.global.education.controller.dto.User;
import com.global.education.controller.handler.BaseHandler;
import com.global.education.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import javax.validation.Valid;

import static com.global.education.mapper.UserMapper.INSTANCE;

@RestController
@RequestMapping(path = "/user")
public class UserController extends BaseHandler {

    @Autowired
    private UserService userService;

    @GetMapping
    @Secured("ROLE_ADMIN")
    public List<User> getUsers() {
        return INSTANCE.buildUsersResponse(userService.getUsers());
    }

    @GetMapping(path = "/{login}")
    @Secured("ROLE_ADMIN")
    public User getUserByLogin(@PathVariable(name = "login") String login) {
        return INSTANCE.buildFullUser(userService.getUserByLogin(login));
    }

    @GetMapping(path = "/search")
    @Secured("ROLE_ADMIN")
    public List<User> getUsers(@Valid SpecificationRequest request) {   //todo modelAttribute
        return INSTANCE.buildUsersResponse(userService.findUsers(request));

    }

}
