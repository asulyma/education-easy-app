package com.global.shop.controller;

import com.global.shop.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import java.security.Principal;

/**
 * @author Aleksandr Sulyma
 * @version 1.0
 */
@Controller
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping(path = "/")
    public String index() {
        return "external";
    }

    @GetMapping(path = "/moderator")
    @Secured({"ROLE_moderator"})
    public String index2(Principal principal) {
        return "moderator";
    }

    @GetMapping(path = "/admin")
    @Secured({"ROLE_admin"})
    public String index3(Principal principal) {
        return "admin";
    }

    @GetMapping(path = "/customers")
    @Secured({"ROLE_user"})
    public String customers(Principal principal, Model model) {
        model.addAttribute("users", userService.findAll());
        model.addAttribute("username", principal.getName());
        return "customers";
    }

    @GetMapping(path = "/logout")
    public String logout(HttpServletRequest request) throws ServletException {
        request.logout();
        return "/";
    }

}
