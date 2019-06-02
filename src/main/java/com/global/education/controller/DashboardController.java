package com.global.education.controller;

import com.global.education.controller.response.BaseController;
import com.global.education.util.ProjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.security.Principal;

/**
 * Home page. TODO
 * @author Aleksands Sulyma
 * @version 1.0
 */
@Controller
public class DashboardController extends BaseController {

    private final ProjectUtils projectUtils;

    @Autowired
    public DashboardController(ProjectUtils projectUtils) {
        this.projectUtils = projectUtils;
    }

    @GetMapping("/home")
    @Secured("ROLE_user")
    public String index(Principal principal) {

        //TODO make wrapper like BaseController for returns ModelAndView
//        projectUtils.getUserInfo(principal);
//
//        UserEntity userEntityInfo = projectUtils.getUserInfo(principal);
//        ModelAndView modelAndView = new ModelAndView("index");
//        modelAndView.addObject("username", userEntityInfo.getLogin());
//        return modelAndView;
        return "index";
    }

    @GetMapping("/login")
    public String login(Principal principal){
        return "login";
    }
}
