package com.global.education.model.user;

import lombok.Getter;

@Getter
public enum Role {

    ROLE_ADMIN("admin"),
    ROLE_MODERATOR("moderator"),
    ROLE_USER("user");

    private final String description;

    Role(String description) {
        this.description = description;
    }
}
