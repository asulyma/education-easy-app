package com.global.shop.model.user;

/**
 * @author Aleksandr Sulyma
 * @version 1.0
 */
public enum Role {

    ADMIN("admin"),
    MODERATOR("moderator"),
    USER("user");

    public final String description;

    Role(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
