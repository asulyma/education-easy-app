package com.global.education.model.user;

import lombok.Getter;

/**
 * @author Aleksandr Sulyma
 * @version 1.0
 */
@Getter
public enum Role {

    ADMIN("admin"),
    MODERATOR("moderator"),
    USER("user");

    private final String description;

    Role(String description) {
        this.description = description;
    }
}
