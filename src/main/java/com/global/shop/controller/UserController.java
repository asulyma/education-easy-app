package com.global.shop.controller;

import com.global.shop.controller.response.BaseController;
import com.global.shop.controller.response.BaseResponse;
import com.global.shop.model.user.User;
import com.global.shop.model.wrapper.UserWrapper;
import com.global.shop.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author Aleksandr Sulyma
 * @version 1.0
 */
@RestController
@RequestMapping("/users")
public class UserController extends BaseController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }


    @GetMapping
    @Secured({"ROLE_admin"})
    public BaseResponse<List<UserWrapper>> getAllUsers() {
        return new BaseResponse<>(userService.getListOfUsers());
    }


    @GetMapping(path = "/{login}")
    @Secured({"ROLE_admin"})
    public BaseResponse<User> getUserByLogin(@PathVariable(name = "login") String login) {
        return new BaseResponse<>(userService.getUserByLogin(login));
    }


}
