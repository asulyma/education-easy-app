package com.global.education.controller.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

import javax.validation.constraints.NotNull;

@Getter
@Setter
public class Lesson {

    private Long id;

    @NotNull
    private String title;
    private String description;
    @NotNull
    private Long courseId;
    private List<Comment> comments;
}
