package com.global.auth.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
public class ResourceInfoController {

    @GetMapping("/user/me")
    public Principal user(Principal principal) {
        return principal;
    }

}