package com.global.education.model.notification;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
public enum EntityType {

    COURSE("course"),
    SECTION("section"),
    LESSON("lesson");

    @Getter
    private String label;

}
