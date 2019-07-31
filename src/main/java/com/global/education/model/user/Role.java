package com.global.education.model.user;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
public enum Role {

    ROLE_ADMIN("admin"),
    ROLE_MODERATOR("moderator"),
    ROLE_USER("user");

    @Getter
    private final String description;
}
