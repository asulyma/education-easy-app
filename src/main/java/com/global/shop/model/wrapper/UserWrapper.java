package com.global.shop.model.wrapper;

import com.global.shop.model.user.Rank;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

/**
 * @author Aleksandr Sulyma
 * @version 1.0
 */
@Getter
@Setter
public class UserWrapper {

    private String login;
    private String email;
    private boolean isActive;
    private boolean isLocked;
    private Rank rank;
    private LocalDate registrationDate;
    private String roles;
}
