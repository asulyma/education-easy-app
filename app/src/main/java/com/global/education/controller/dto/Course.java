package com.global.education.controller.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
public class Course {

    private Long id;

    @NotNull
    private String title;
    private String description;

    @NotNull
    private Long beginDate;

    @NotNull
    private Long endDate;

    @NotNull
    private Long cost;

}
