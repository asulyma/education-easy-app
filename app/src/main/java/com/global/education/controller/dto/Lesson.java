package com.global.education.controller.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class Lesson {

    private Long id;
    private String title;
    private String description;
    private Long courseId;
    private List<Comment> comments;
}
