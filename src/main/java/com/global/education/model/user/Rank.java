package com.global.education.model.user;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
public enum Rank {

    JAVA_JUNIOR_ONE("Java Junior 1"),
    JAVA_JUNIOR_TWO("Java Junior 2"),
    JAVA_JUNIOR_THREE("Java Junior 3"),
    JAVA_MIDDLE_ONE("Java Middle 1"),
    JAVA_MIDDLE_TWO("Java Middle 2"),
    JAVA_SENIOR("Java Senior");

    @Getter
    private final String description;
}
