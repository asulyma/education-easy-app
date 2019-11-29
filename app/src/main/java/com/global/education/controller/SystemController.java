package com.global.education.controller;

import com.global.education.controller.dto.User;
import com.global.education.service.UserDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping(path = "/system")
public class SystemController {

    @Autowired
    private UserDataService userDataService;

    /**
     * This method implies that the user has already been registered in the OAuth2 server
     */
    @PostMapping("/second-step-register")
    public ResponseEntity<HttpStatus> register(@RequestBody @Valid User user) {
        userDataService.createOrUpdateUserData(user);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

}
