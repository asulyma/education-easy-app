package com.global.education.controller;

import com.global.education.service.UserDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/system")
public class SystemController {

    @Autowired
    private UserDataService userDataService;

    @PostMapping("/second-step-register")
    public ResponseEntity<HttpStatus> register() {
        userDataService.createUserData();
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

}
