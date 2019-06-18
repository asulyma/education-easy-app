package com.global.education.controller.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class LessonResponse {

    private Long id;
    private String title;
    private String description;
    private Long sectionId;
    private List<CommentResponse> comments;
}
