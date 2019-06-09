package com.global.education.controller;

import com.global.education.controller.response.BaseController;
import com.global.education.util.UserUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.security.Principal;

/**
 * Home page for UI testing.
 */
@Controller
public class DashboardController extends BaseController {

    @Autowired
    private UserUtils userUtils;

    @GetMapping("/home")
    public String index(Principal principal) {
        return "index";
    }

    @GetMapping("/login")
    public String login(Principal principal) {
        return "login";
    }
}
