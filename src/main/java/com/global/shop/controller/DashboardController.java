package com.global.shop.controller;

import com.global.shop.model.user.User;
import com.global.shop.util.ProjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

/**
 * @author Aleksands Sulyma
 * @version 1.0
 */
@RestController
public class DashboardController {

    private final ProjectUtils projectUtils;

    @Autowired
    public DashboardController(ProjectUtils projectUtils) {
        this.projectUtils = projectUtils;
    }

    @GetMapping
    public String index(Principal principal) {

        User user = projectUtils.getUser(principal);

        return "external";
    }
}
