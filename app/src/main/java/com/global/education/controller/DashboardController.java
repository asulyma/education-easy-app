package com.global.education.controller;

import com.global.education.controller.handler.BaseHandler;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.security.Principal;

/**
 * Home page for UI testing.
 */
@Controller
public class DashboardController extends BaseHandler {

    @GetMapping("/test")
    @ResponseBody
    public String index(Principal principal) {
        return "Successfully login";
    }

}
