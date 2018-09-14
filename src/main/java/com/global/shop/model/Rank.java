package com.global.shop.model;

/**
 * @author Aleksandr Sulyma
 * @version 1.0
 */
public enum Rank {

    JAVA_JUNIOR_ONE("Java Junior 1"),
    JAVA_JUNIOR_TWO("Java Junior 2"),
    JAVA_JUNIOR_THREE("Java Junior 3"),
    JAVA_MIDDLE_ONE("Java Middle 1"),
    JAVA_MIDDLE_TWO("Java Middle 2"),
    JAVA_SENIOR("Java Senior");

    public final String description;

    Rank(String description) {
        this.description = description;
    }
}
