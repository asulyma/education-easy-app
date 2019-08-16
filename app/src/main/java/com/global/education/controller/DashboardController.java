package com.global.education.controller;

import com.global.education.controller.handler.BaseHandler;
import com.global.education.util.UserUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Home page for UI testing.
 */
@Controller
public class DashboardController extends BaseHandler {

    @GetMapping("/test")
    @ResponseBody
    public String index() {
        UserUtils.currentUser();
        return "Successfully login";
    }

}
