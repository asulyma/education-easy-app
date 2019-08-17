package com.global.education.controller.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
public class User {

    private Long id;
    private String login;
    private String name;
    private String email;
    private boolean active;
    private List<Long> alreadyDoneLessons = new ArrayList<>();
}
