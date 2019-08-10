package com.global.education.controller.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Course {

    private Long id;
    private Long createdDate;
    private String title;
    private String description;
    private Long beginDate;
    private Long endDate;
    private Long cost;
}
