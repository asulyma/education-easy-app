package com.global.education.controller.dto;

import com.global.education.model.user.Progress;
import com.global.education.model.user.Rank;
import com.global.education.model.user.Role;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class User {

    private Long id;
    private String login;
    private String name;
    private Integer age;
    private String email;
    private boolean active;
    private Rank rank;
    private Role role;
    private String gender;
    private List<Long> allowedCourses;
    private List<Long> alreadyDoneLessons;
    private Progress progress;
}
