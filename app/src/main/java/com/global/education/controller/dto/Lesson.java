package com.global.education.controller.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
public class Lesson {

    private Long id;
    @NotNull
    private String title;
    @NotNull
    private Long courseId;
    private Long executionTime;
}
