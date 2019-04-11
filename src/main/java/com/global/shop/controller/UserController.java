package com.global.shop.controller;

import com.global.shop.controller.response.BaseController;
import com.global.shop.controller.response.BaseResponse;
import com.global.shop.mapper.UserMapper;
import com.global.shop.model.wrapper.UserResponse;
import com.global.shop.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author Aleksandr Sulyma
 * @version 1.0
 */
@RestController
@RequestMapping("/users")
public class UserController extends BaseController {

    private final UserService userService;
    private final UserMapper mapper;

    @Autowired
    public UserController(UserService userService, UserMapper mapper) {
        this.userService = userService;
        this.mapper = mapper;
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
