package com.global.shop.model.wrapper;

import com.global.shop.model.user.Rank;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

/**
 * @author Aleksandr Sulyma
 * @version 1.0
 */
@Getter
@Setter
public class UserResponse {

    private Long id;
    private String login;
    private String givenName;
    private String familyName;
    private Integer age;
    private String email;
    private boolean isLocked;
    private boolean isActive;
    private Rank rank;
    private String roles;
    private LocalDate birthDate;
    private String gender;
    private LocalDate registrationDate;

    private List<String> allowedCourses;
    private Map<String, Long> progress;
}
