package com.education.common.model;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
public enum Rank {

    TRAINEE("Trainee"),
    JUNIOR("Junior"),
    UPPER_JUNIOR("Upper Junior"),
    MIDDLE("Middle"),
    UPPER_MIDDLE("Upper Middle"),
    SENIOR("Senior");

    @Getter
    private final String description;
}
