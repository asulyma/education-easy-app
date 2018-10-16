package com.global.shop.controller;

import com.global.shop.controller.response.BaseController;
import com.global.shop.util.ProjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.security.Principal;

/**
 * Home page. TODO
 *
 * @author Aleksands Sulyma
 * @version 1.0
 */
@RestController
@RequestMapping("/")
public class DashboardController extends BaseController {

    private final ProjectUtils projectUtils;

    @Autowired
    public DashboardController(ProjectUtils projectUtils) {
        this.projectUtils = projectUtils;
    }

    @GetMapping
    public ModelAndView index(Principal principal) {

        //TODO make wrapper like BaseController for returns ModelAndView
        projectUtils.getUserInfo(principal);

        return new ModelAndView("external");
    }
}
