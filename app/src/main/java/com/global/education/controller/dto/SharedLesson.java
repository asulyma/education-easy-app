package com.global.education.controller.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class SharedLesson extends Lesson {

    private String description;
    private List<Comment> comments;

}
