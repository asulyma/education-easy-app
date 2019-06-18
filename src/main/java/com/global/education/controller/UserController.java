package com.global.education.controller;

import com.global.education.controller.response.BaseController;
import com.global.education.controller.response.BaseResponse;
import com.global.education.controller.dto.UserResponse;
import com.global.education.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static com.global.education.mapper.UserMapper.INSTANCE;

@RestController
@RequestMapping("/user")
public class UserController extends BaseController {

    @Autowired
    private UserService userService;

    @GetMapping
    @Secured("ROLE_ADMIN")
    public BaseResponse<List<UserResponse>> getUsers() {
        return new BaseResponse<>(INSTANCE.buildUsersResponse(userService.getUsers()));
    }

    @GetMapping(path = "/{login}")
    @Secured("ROLE_ADMIN")
    public BaseResponse<UserResponse> getUserByLogin(@PathVariable(name = "login") String login) {
        return new BaseResponse<>(INSTANCE.buildFullUser(userService.getUserByLogin(login)));
    }

}
