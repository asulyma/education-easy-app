package com.global.education.controller;

import com.global.education.controller.response.BaseController;
import com.global.education.controller.response.BaseResponse;
import com.global.education.mapper.UserMapper;
import com.global.education.model.wrapper.UserResponse;
import com.global.education.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController extends BaseController {

    private final UserService userService;
    private final UserMapper mapper = UserMapper.INSTANCE;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    @Secured({"ROLE_admin"})
    public BaseResponse<List<UserResponse>> getAllUsers() {
        return new BaseResponse<>(mapper.buildUsersResponse(userService.getListOfUsers()));
    }

    @GetMapping(path = "/{login}")
    @Secured({"ROLE_admin"})
    public BaseResponse<UserResponse> getUserByLogin(@PathVariable(name = "login") String login) {
        return new BaseResponse<>(mapper.buildFullUser(userService.getUserByLogin(login)));
    }

}
