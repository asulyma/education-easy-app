package com.global.education.controller;

import com.global.education.controller.handler.BaseHandler;
import com.global.education.util.UserUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.security.Principal;

/**
 * Home page for UI testing.
 */
@Controller
public class DashboardController extends BaseHandler {

    @Autowired
    private UserUtils userUtils;

    @GetMapping("/api")
    @ResponseBody
    public String index(Principal principal) {
        return "Successfully login";
    }

    @GetMapping("/home")
    public String homePage(Principal principal) {
        return "index";
    }

}
