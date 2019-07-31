package com.global.education.controller.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Section {

    private Long id;
    private String title;
    private String description;
    private Long courseId;
}
